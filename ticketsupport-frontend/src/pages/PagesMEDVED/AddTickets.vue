
<template>
  <div class="row">
    <div class="col-md-6">
      <card class="stacked-form" title="Stacked Form">
        <h4 slot="header" class="card-title">New Ticket</h4>
        <form @submit.prevent>
          <div>
            <base-input
              label="Title"
              type="text"
              placeholder="Title"
              v-model="ticket.title"
            ></base-input>

            <div class="col-md-12">
              <base-input label="Description">
                <textarea
                  class="form-control"
                  placeholder="Description"
                  v-model="ticket.description"
                ></textarea>
              </base-input>
            </div>

            <div class="col-md-12">
              <div class="row">
                <div class="col-md-4">
                  <h4 class="card-title">Select priority</h4>
                  <el-select
                    class="select-primary"
                    size="large"
                    placeholder="Priority select"
                    v-model="ticket.priority
                  "
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
                    v-model="ticket.agent"
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
            >Create</base-button>
          </div>
        </form>
      </card>
    </div>
  </div>
</template>
<script>
import { Select, Option } from "element-ui";
import { ImageUpload } from "src/components/index";

import apiMedved from "src/apiMedved.js";

export default {
  components: {
    [Option.name]: Option,
    [Select.name]: Select,
    ImageUpload
  },

  data() {
    return {
      ticket: {
        title: "",
        description: "",
        enterprise: "",
        priority: "",
        agent: "",
      },
      priorities: [],
      agents: []
    };
  },
  created() {

    apiMedved
      .getAllPriorities()
      .then(pri => (this.priorities = pri))
      .catch();

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

  },

  methods: {
    sendTicket() {
      //this.$set(this.ticket, 'foto', this.images.regular);
      apiMedved.saveNewTicket(JSON.stringify(this.ticket));

      this.$router.push("/tickets/" + "");
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
