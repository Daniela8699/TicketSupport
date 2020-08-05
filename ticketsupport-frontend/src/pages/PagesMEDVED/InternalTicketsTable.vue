<template>
    <card>    
      
      <div class="row">
        <base-input class="col-md-4" addonLeftIcon="el-icon-notebook-2" v-model="searchQuery"  placeholder="Search Records"/>
        <base-button  class="col-md-1/2" type="primary" size="sm" icon @click="findRegisters" >
          <i class="tim-icons icon-zoom-split"></i>
        </base-button>
      </div>

      <div slot="header"><h4 class="card-name">{{tkttype}} Tickets</h4></div>
    <div class="row">
          <div class="col-sm-12">
            <el-table :data="itkt">
              <el-table-column min-width="150" sortable prop = "prioridad.nombre" label="Priority" align="center" >
              <template slot-scope="scope"  align="center">
                    <div v-if= "scope.row.prioridad.nombre === 'Low'">
                      <i class="tim-icons icon-time-alarm" style="color:rgb(6,194,6)"></i>
                    </div>
                    <div v-else-if= "scope.row.prioridad.nombre === 'Medium'">
                        <i class="tim-icons icon-time-alarm" style="color:yellow"></i>
                    </div>
                    <div v-else>
                        <i class="tim-icons icon-time-alarm" style="color:red"></i>
                    </div>
                </template>
              </el-table-column>
              <el-table-column sortable min-width="150" prop="ticket.title" label="Title" align="center">
              </el-table-column>
              <el-table-column
                min-width="150"
                sortable
                prop="ticketState.stateName"
                align="center"
                label="State"
                
              >
                <template slot-scope="scope"  align="center">
                  <div >
                    {{scope.row.ticketState.stateName}}
                  </div>
                </template>
              </el-table-column>
              <el-table-column
                min-width="150"
                prop="ticket.openDate"
                align="center"
                label="Creation Date"
              >
              
              </el-table-column>
              <el-table-column
                min-width="150"
                prop="ticket.closeDate"
                align="center"
                label="Closed Date"
              >
              </el-table-column>
              <el-table-column
                min-width="170"
                prop="ticket.lastActivityDate"
                align="center"
                label="Last Activity Date"
              >
              </el-table-column>
              <el-table-column
                min-width="150"
                sortable
                prop="enterprise.name"
                align="center"
                label="Enterprise"
              >
              </el-table-column>
              <el-table-column
                min-width="150"
                prop="userCreator"
                align="center"
                label="Creator"
              >
              <template slot-scope = "scope" align  = "center">
                <div>
                  {{scope.row.userCreator.name}} {{scope.row.userCreator.lastName}}
                </div>
              </template>

            </el-table-column>
            <el-table-column
              min-width="240"
              header-align="center"
              prop="ticket.id"
              align="center"
              label="Actions"
            >
              <div
                slot-scope="scope"
                class="text-center table-actions"
              >

                <el-tooltip
                  content="More Details"
                  effect="light"
                  :open-delay="300"
                  placement="top"
                >
                  <base-button
                    type= "info"
                    icon
                    size="sm"
                    class="btn-link"
                    @click="moreDetails(scope.row.ticket.id)"
                  >
                    <i class="tim-icons icon-notes"></i>
                  </base-button>
                </el-tooltip>


                <el-tooltip
                  content="Answer"
                  effect="light"
                  :open-delay="300"
                  placement="top"
                >
                
                  <base-button
                    type= "success"
                    icon
                    size="sm"
                    class="btn-link"
                    @click="answer(scope.row.ticket.id)"
                  >
                    <i class="tim-icons icon-send"></i>
                  </base-button>
                </el-tooltip>

                  <el-tooltip
                    content="Edit"
                    effect="light"
                    :open-delay="300"
                    placement="top"
                  >
                      <base-button
                        type= "warning"
                        icon
                        size="sm"
                        class="btn-link"
                        @click="editTicket(scope.row.ticket.id)"
                      >
                        <i class="tim-icons icon-pencil"></i>
                      </base-button>
                    </el-tooltip>
                  
                </div>
              </el-table-column>
            </el-table>
          </div>
          <div style="margin-top:2%" slot="footer" class="col-12 d-flex justify-content-center justify-content-md-between" >
            <div class="col">
              <p class="card-category"> Showing {{ from + 1 }} to {{ to }} of {{ total }} Tickets
              </p>
            </div>
            
            <div class="row">
              <div class="col-md-3/2">
                <base-input >
                    <select class="form-control" style="color:#e14eca" v-model="pagination.perPage">
                      <option
                        style="background-color: #1E1E26"
                        v-for="item in pagination.perPageOptions"
                        :key="item"
                        :label="item"
                        :value="item"
                      ></option>
                      
                    </select>
                  </base-input>
              </div>
              
                <base-pagination class="pagination-no-border" v-model="pagination.currentPage" :per-page = "pagination.perPage"
                  :total = "total" @input="findRegisters">
                </base-pagination>
            </div>
                
          </div>
      </div>      
   </card>
</template>

<script>
import { Table, TableColumn,Select, Option, Column} from 'element-ui';
import { BaseCheckbox, BaseProgress, RouteBreadCrumb,BasePagination } from 'src/components';
import ShoppingTable from '../Tables/ExtendedTables/ShoppingTable';
import { rebind } from 'd3';
import apiMedved from "@/apiMedved";

export default {
  props:['tkttype','itkt', 'total'],
  components: {
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
    BaseCheckbox,
    RouteBreadCrumb,
    BaseProgress,
    ShoppingTable,
    BasePagination,
    [Select.name]: Select,
    [Option.name]: Option,
  },
  data() {
    return {
      pagination: {
        perPage: 5,
        currentPage: 1,
        perPageOptions: [5, 10, 25, 50]
      },

      defaultProps: {
          children: 'children',
          label: 'label'
        }
      ,
      searchQuery:'', 
    };
  },
  computed:{
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
  },
  created(){
    this.findRegisters();
  },
   methods: {
     
     formatDate(time){
     

        var t = new Date(time)

       return t.toLocaleString("sv-SE")
      },
   
     editTicket(id){
         this.$router.push({path: '/editTicket/'+id});    
         return;
     },
     deleteTicket(id){
         apiMedved.deleteTicket(id).then(res => console.log(res));
         return;
     },
     findRegisters(){
      
      let parameters= this.searchQuery+";"+this.pagination.perPage+";"+this.pagination.currentPage+";"+this.tkttype;
      this.$emit("metaSearchInternal",parameters)
     },
     moreDetails(id){
       this.$router.push({path: '/ticket/'+id});
        return;   
     },
      answer(id){
       this.$router.push({path: '/answer/'+id});
        return;   
     }
    }
};
</script>
<style lang="scss">
.checkbox-cell {
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
}
ul li, ol li {
  color: darkgrey;

}
</style>