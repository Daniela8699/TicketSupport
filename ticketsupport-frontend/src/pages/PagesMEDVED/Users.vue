<template>
  <div class="content">
    <div class="col-md-8 ml-auto mr-auto">
      <h2 class="text-center">Users</h2>
     
    </div>
    <div class="row mt-5">
      <div class="col-12">
        <card card-body-classes="table-full-width">
          <h4 slot="header" class="card-title">Users</h4>
          <div>
            <div class="row">
              <div class="col-md-4">
              <base-input>
                <el-input
                  type="search"
                  class="mb-3 search-input"
                  prefix-icon="el-icon-search"
                  placeholder="Search records"
                  v-model="searchQuery"
                  aria-controls="datatables"
                >
                </el-input>
              </base-input>
            </div>
            <div
              class="col-md-1 d-flex justify-content-center justify-content-sm-between flex-wrap"
            >
              <el-select
                class="select-primary mb-3 pagination-select"
                v-model="pagination.perPage"
                placeholder="Per page"
              >
                <el-option
                  class="select-primary"
                  v-for="item in pagination.perPageOptions"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </div>
            
            </div>
            <el-table :data="queriedData">
              <el-table-column
                v-for="column in tableColumns"
                :key="column.label"
                :min-width="column.minWidth"
                :prop="column.prop"
                :label="column.label"
              >
              </el-table-column>
              <el-table-column :min-width="135" align="right" label="Actions">
                <div slot-scope="props">
                  <base-button
                    @click.native="handleEdit(props.row)"
                    class="edit btn-link"
                    type="warning"
                    size="sm"
                    icon
                  >
                    <i class="tim-icons icon-pencil"></i>
                  </base-button>
                  <base-button
                    @click.native="handleDelete(props.row)"
                    class="remove btn-link"
                    type="danger"
                    size="sm"
                    icon
                  >
                    <i class="tim-icons icon-simple-remove"></i>
                  </base-button>
                </div>
              </el-table-column>
            </el-table>
          </div>
          <div
            slot="footer"
            class="col-12 d-flex justify-content-center justify-content-sm-between flex-wrap"
          >
            <div class="">
              <p class="card-category">
                Showing {{ from + 1 }} to {{ to }} of {{ total }} entries
              </p>
            </div>
            <base-pagination
              class="pagination-no-border"
              v-model="pagination.currentPage"
              :per-page="pagination.perPage"
              :total="total"
            >
            </base-pagination>
            <base-button
              v-on:click="addUs"
              class="mt-3"
              native-type="submit"
              type="primary"
            >Add User</base-button>
          </div>
        </card>
      </div>
    </div></div
></template>
<script>
import { Table, TableColumn, Select, Option } from 'element-ui';
import { BasePagination } from 'src/components';

import Fuse from 'fuse.js';
import swal from 'sweetalert2';
import apiMedved from "src/apiMedved.js";

export default {
  components: {
    BasePagination,
    [Select.name]: Select,
    [Option.name]: Option,
    [Table.name]: Table,
    [TableColumn.name]: TableColumn
  },
  computed: {
    /***
     * Returns a page from the searched data or the whole data. Search is performed in the watch section below
     */
    queriedData() 
    {
      let result = this.tableData;
      if (this.searchedData.length > 0) 
      {
        result = this.searchedData;
      }
      console.log(this.searchedData)
      return result.slice(this.from, this.to);
    },
    to() {
      let highBound = this.from + this.pagination.perPage;
      if (this.total < highBound) {
        highBound = this.total;
      }
      return highBound;
    },
    from() {
      return this.pagination.perPage * (this.pagination.currentPage - 1);
    },
    total() {
      return this.searchedData.length > 0
        ? this.searchedData.length
        : this.tableData.length;
    }
  },
  data() {
    return {
      pagination: {
        perPage: 5,
        currentPage: 1,
        perPageOptions: [5, 10, 25, 50],
        total: 0
      },
      searchQuery: '' ,
      propsToSearch: ['username', 'email','lastName','name'],
      tableColumns: [
        {
          prop: 'username',
          label: 'UserName',
          minWidth: 120
        },
        {
          prop: 'email',
          label: 'email',
          minWidth: 200
        },
        {
          prop: 'name',
          label: 'Name',
          minWidth: 100
        },
        {
          prop: 'lastName',
          label: 'Last Name',
          minWidth: 100
        },
        {
          prop: 'enterprises.name',
          label: 'Company',
          minWidth: 120
        }
      ],
      tableData: [],
      searchedData: [],
      fuseSearch: null,
      
    };
  },
  created(){

    apiMedved
      .getAllUsers()
      .then(emp => (this.tableData = emp))
      .catch();
      

  },
  methods: {
    handleLike(index, row) {
      swal({
        title: `You liked ${row.name}`,
        buttonsStyling: false,
        type: 'success',
        confirmButtonClass: 'btn btn-success btn-fill'
      });
    },
    handleEdit(row) {
    
    
     this.$router.push("/editUser/" + row.username);
      
      
    },
    addUs(){
      this.$router.push("/addUser");
    },

    handleDelete(row) {
      swal({
        title: 'Are you sure?',
        text: `You won't be able to revert this!`,
        type: 'warning',
        showCancelButton: true,
        confirmButtonClass: 'btn btn-success btn-fill',
        cancelButtonClass: 'btn btn-danger btn-fill',
        confirmButtonText: 'Yes, delete it!',
        buttonsStyling: false
      }).then(result => {
        if (result.value) {
         // this.deleteRow(row);
          apiMedved.deleteUser(row.username);
          this.$router.go();
        }   
      });
    },
    deleteRow(row) {
      let indexToDelete = this.tableData.findIndex(
        tableRow => tableRow.id === row.id
      );
      if (indexToDelete >= 0) {
        this.tableData.splice(indexToDelete, 1);
      }
    }
  },
  mounted() {
    
  },
  watch: {
    /**
     * Searches through the table data by a given query.
     * NOTE: If you have a lot of data, it's recommended to do the search on the Server Side and only display the results here.
     * @param value of the query
     */
    searchQuery(value) 
    {
      this.fuseSearch = new Fuse(this.tableData, {keys: this.propsToSearch, threshold: 0.3});
      let result = this.tableData;
      if (value !== '') {
        result = this.fuseSearch.search(this.searchQuery);
      }
      console.log(result)
      this.searchedData = result;
    }
  }
};
</script>
<style>
.pagination-select,
.search-input {
  width: 200px;
}
</style>
