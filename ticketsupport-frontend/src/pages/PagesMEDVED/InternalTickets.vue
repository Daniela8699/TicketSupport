<template>
  <div>
    <card style="height: 3rem;">
      <h4 class="card-title">Internal Tickets</h4>
      <div class="TabPanel">
        <Tabs>
            <TabPane label = "Assigned">
                <InternalTicketsTable :tkttype="'Assigned'" :itkt="internaltktassigned"  :total="totalassigned" @metaSearchInternal="metaSearchInternal"/>
            </TabPane>
            <TabPane label = "Resolved">
                <InternalTicketsTable :tkttype="'Resolved'" :itkt="internaltktresolved"  :total="totalresolved" @metaSearchInternal="metaSearchInternal"/>
            </TabPane>
        </Tabs>
      </div>
    </card>
  </div>
</template>

<script>
import InternalTicketsTable from "./InternalTicketsTable";
import { Tabs, TabPane } from "src/components";
import apiMedved from "@/apiMedved";

export default {
 components: {
    InternalTicketsTable,
    Tabs,
    TabPane
  },
  name: "internalTickets",
  data() {
    return {
      internaltktresolved: [],
      internaltktassigned: [],
      totalresolved : 0,
      totalassigned : 0
    };
  },
  methods: {
      metaSearchInternal(keyword){
      let parameter= keyword.split(";");
      if(parameter[3]=="Resolved")
      {
          this.resolvedTickets(parameter);
      }else if(parameter[3]=="Assigned")
      {
          this.assignedTickets(parameter);
      }
      
    },

    resolvedTickets(parameter)
    {
        if(parameter[0].trim() ===""){
        apiMedved
        .getAllInternalTicketResolved(parameter[1],parameter[2])
        .then(tkt => (this.internaltktresolved = tkt.content,this.totalresolved = tkt.totalElements))
        .catch();
      }else{
        apiMedved
        .findByKeyWordInternalResolved(parameter[0],parameter[1],parameter[2])
        .then(tkt => (this.internaltktresolved = tkt.content,this.totalresolved = tkt.totalElements))
        .catch();
      }
    },
    assignedTickets(parameter)
    {
        if(parameter[0].trim() ===""){
        apiMedved
        .getAllInternalTicketAssigned(parameter[1],parameter[2])
        .then(tkt => (this.internaltktassigned = tkt.content,this.totalassigned = tkt.totalElements))
        .catch();
      }else{
        apiMedved
        .findByKeyWordInternalAssigned(parameter[0],parameter[1],parameter[2])
        .then(tkt => (this.internaltktassigned = tkt.content,this.totalassigned = tkt.totalElements))
        .catch();
      }
    }

  }
}
</script>

<style scoped>
.TabPanel {
  margin-top: 4%;
}
</style>