<template>
  <div class="row"><h3>Reports and notifications</h3>
  
  <card style="height: 4rem;">
      <h3 class="card-title">
        Reports
      </h3>
    </card>

    
           
                
                          <card card-body-classes="table-full-width">
                            <h4 slot="header" class="card-title">Logs History</h4>
                            <el-table :data="this.tableData">
                              <el-table-column
                                v-for="column in tableColumns"
                              
                                    :key="column.label"
                                    :min-width="column.minWidth"
                                    :prop="column.prop"
                                    :label="column.label"
                                    
                              ></el-table-column>
                            
                            </el-table>
                          </card>
                    

  
    
  </div>
</template>
<script>
import { Table, TableColumn,Select, Option } from 'element-ui';
import config from '@/config';
import apiMedved from "src/apiMedved.js";
import { Tabs, TabPane } from "src/components";

export default {
  name: 'reportes',
  components: {
    [Select.name]: Select,
    [Option.name]: Option,
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
  
  },
 
  data() {
    return {

     // searchQuery: '',
     // propsToSearch: ['name', 'contactMail', 'contactPhone','nit'],
      tableColumns: [
        {
          prop: 'id',
          label: 'id',
          minWidth: 120
        },
        {
          prop: 'deletedTickets',
          label: 'deletedTickets',
          minWidth: 100
        },
        {
          prop: 'savedTickets',
          label: 'savedTickets',
          minWidth: 100
        },
        {
          prop: 'secondsToComplete',
          label: 'secsToComplete',
          minWidth: 120
        },
        {
          prop: 'initialDate',
          label: 'initialDate',
          minWidth: 120
        },
        {
          prop: 'finalDate',
          label: 'finalDate',
          minWidth: 120
        }
      ],
      tableData: []
    };
  },
  formatDate(time){
     

        var t = new Date(time)

       return t.toLocaleString("sv-SE")
      },
  created(){

    apiMedved
      .getAllSynchReports()
      .then(emp => (this.tableData = emp))
      .catch();

    
  },
};

</script>
<style></style>
