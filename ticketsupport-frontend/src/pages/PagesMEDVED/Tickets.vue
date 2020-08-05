<template>
  <div>
    <card style="height: 3rem;">
      <h3 class="card-title">Tickets</h3>
      <div class="TabPanel">
        <Tabs>
          <TabPane label="External Ticket">
            <ExternalTicketsTable :etkt="externaltkt" :total="totaletkt" @metaSearchExternal="metaSearchExternal"/>
          </TabPane>
          <TabPane label="Internal Ticket">
              <InternalTickets/>
          </TabPane>
        </Tabs>
      </div>
    </card>
  </div>
</template>
<script>
import ExternalTicketsTable from "./ExternalTicketsTable";
import InternalTickets from "./InternalTickets";
import { Tabs, TabPane } from "src/components";

import apiMedved from "@/apiMedved";
export default {
  components: {
    ExternalTicketsTable,
    InternalTickets,
    Tabs,
    TabPane
  },
  name: "tickets",
  data() {
    return {
      internaltkt: [],
      externaltkt: [],
      totaletkt : 0,
      totalitkt : 0
    };
  },
  created() {
    /*
    apiMedved
      .getAllExternalTicket(5,0)
      .then(tkt => (this.externaltkt = tkt))
      .catch();
    apiMedved
      .getAllInternalTicket(5,0)
      .then(tkt => (this.internaltkt = tkt))
      .catch();
      */
  },
  methods: {
    
    metaSearchExternal(keyword){
      let parameter= keyword.split(";");
      
      
      if(parameter[0].trim() ===""){
        apiMedved
        .getAllExternalTicket(parameter[1],parameter[2])
        .then(tkt => (this.externaltkt = tkt.content,this.totaletkt = tkt.totalElements))
        .catch();
      }else{
        apiMedved
        .findByKeyWordExternal(parameter[0],parameter[1],parameter[2])
        .then(tkt => (this.externaltkt = tkt.content,this.totaletkt = tkt.totalElements))
        .catch();
      }
    },
  }
};
</script>
<style>
.TabPanel {
 margin-bottom: 0%;
}
</style>
