<template>
  <div class="row">
    <div class="col-md-6">
      <card class="stacked-form" title="Stacked Form">
        <h4 slot="header" class="card-title">Edit Ticket</h4>
        <form @submit.prevent>
          <div>
            <base-input label="Title" type="text" placeholder="Title" v-model="tick.ticket.title"></base-input>

            <div class="col-md-12">
              <base-input label="Description">
                <textarea
                  class="form-control"
                  placeholder="Description"
                  v-model="tick.ticket.description"
                ></textarea>
              </base-input>
            </div>

            <div class="col-md-12">
              <div class="row">

                <div class="col-md-4">
                  <h4 class="card-title">Edit priority</h4>
                  <el-select
                    class="select-primary"
                    size="large"
                    placeholder="Priority"
                    v-model="tick.prioridad.id"
                  >
                    <el-option
                      v-for="option in priorities"
                      class="select-primary"
                      :value="option.id"
                      :label="option.nombre"
                      :key="option.nombre"
                    ></el-option>
                  </el-select>
                </div>

                 <div class="col-md-6">
                  <h4 class="card-title">Assign Agent</h4>
                  <el-select
                    class="select-primary"
                    size="large"
                    placeholder="Agent select"
                    v-model="tick.userAssigned.username"
                  >
                    <el-option
                      v-for="option in agents"
                      class="select-primary"
                      :value="option.username"
                      :label="option.fullname"
                      :key="option.username"
                    >
                      
                    </el-option>
                  </el-select>
                </div>
              </div>
            </div>

            <base-button
              v-on:click="sendTicket"
              class="mt-3"
              native-type="submit"
              type="primary"
            >Edit</base-button>
          </div>
        </form>
      </card>
    </div>
  </div>
</template>
<script>
import { Select, Option } from "element-ui";

import apiMedved from "@/apiMedved.js";

export default {
  components: {
    [Option.name]: Option,
    [Select.name]: Select
  },

  data() {
    return {
      tick: null,
      agents: [],
      priorities: [],

      tktR: {
        id: "",
        title: "",
        description: "",
        enterprise: "",
        priority: "",
        agent: "",
      }
    };
  },
  created() {
    apiMedved
      .getInternalTicket(this.$route.params.id)
      .then(tkt => {this.tick = tkt, console.log(tkt)});

    apiMedved
      .getAgentsbyCompany()
      .then(agents => {
          agents.forEach(element => {
            let agn = element;
            agn.fullname = agn.name+ " "+ agn.lastName;
            this.agents.push(agn);
          }
        )
      }
      ).catch(err => console.log(err))

    apiMedved
      .getAllPriorities()
      .then(pri => (this.priorities = pri))
      .catch();
  },
  methods: {
    metodoMaestroQueSolucionaLaVidaYLaMuerteDeLosTickets() {
      this.tktR.id = this.$route.params.id;
      this.tktR.title = this.tick.ticket.title;
      this.tktR.description = this.tick.ticket.description;
      this.tktR.enterprise = this.tick.enterprise.nit;
      this.tktR.priority = this.tick.prioridad.id;
      this.tktR.agent = this.tick.userAssigned.username;
    },

    sendTicket() {
      this.metodoMaestroQueSolucionaLaVidaYLaMuerteDeLosTickets();
      //this.$set(this.ticket, 'foto', this.images.regular);
      apiMedved.editTicket(JSON.stringify(this.tktR)).then(()=>{ this.$router.push("/ticket/" + this.tktR.id);});

     
    }
  }
};
</script>

<style>
.row .el-select {
  width: 100%;
  margin-bottom: 30px;
}
</style>
