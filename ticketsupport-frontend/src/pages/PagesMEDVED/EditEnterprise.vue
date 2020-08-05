
<template>
  <div class="row">
    <div class="col-md-6">
      <card class="stacked-form" title="Stacked Form">
        <h4 slot="header" class="card-title">Edit Enterprise</h4>
        <form @submit.prevent>
          <div>
            <fieldset disabled>
            <base-input label="NIT"
              type="text"
              placeholder="NIT"
              name="NIT"
              v-validate='modelValidations.NIT'
              :error="getError('NIT')"
              required
              v-model="enterprise.nit"></base-input>
            </fieldset>
            <base-input
              label="Name"
              type="text"
              placeholder="Name"
              required
              name="name"
              v-validate='modelValidations.name'
              :error="getError('name')"
              v-model="enterprise.name"
            ></base-input>

            <div class="col-md-12">
              <base-input label="Description"
                :error="getError('description')"
                required
                >
                <textarea
                  class="form-control"
                  placeholder="Description"
                  name="description"
                  v-validate='modelValidations.description'

                  v-model="enterprise.description"
                ></textarea>
              </base-input>
            </div>

            <base-input
              label="Mail contact"
              type="email"
              placeholder="example@example.com"
              required
              name="mail"
              v-validate='modelValidations.mail'
              :error="getError('mail')"
              v-model="enterprise.contactMail"
            ></base-input>

            <base-input
              label="Contact Phone"
              type="numeric"
              placeholder="33333"
              required
              name="phone"
              v-validate='modelValidations.phone'
              :error="getError('phone')"
              v-model="enterprise.contactPhone"
            ></base-input>

            <h4 class="card-title">Assign Available Sources</h4>
            <el-select
              multiple
              class="select-primary"
              collapse-tags
              size="large"
              placeholder="Select Sources"
              v-model="entrKr.sources"
            >
              <el-option
                v-for="option in sources"
                class="select-primary"
                :value="option.id"
                :label="option.sourceName"
                :key="option.sourceName"
              ></el-option>
            </el-select>

            <base-button
              v-on:click="validate"
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

import apiMedved from "src/apiMedved.js";

export default {
  components: {
    [Option.name]: Option,
    [Select.name]: Select
  },

  data() {
    return {
      
      enterprise: null,

      entrKr: {
        nit: "",
        contactMail: "",
        contactPhone: "",
        description: "",
        name: "",
        listNameUsers: [],
        sources: []
      },

      modelValidations: {
        NIT: {
          required: true,
        },
        name: {
          required:true
        },
        description: {
          required:true
        },
        mail: {
          email:true,
          required:true
        },
        phone: {
          required:true,
          numeric:true
        }
      },

      users: [],

      sources: []
    };
  },
  created() {

  apiMedved
      .getAllSources()
      .then(sour => (this.sources = sour));
  apiMedved
      .getAllUsersByEnterprise(this.$route.params.nit)
      .then(us => (this.users = us));

  apiMedved
      .getEnterprise(this.$route.params.nit)
      .then(tkt => (this.enterprise = tkt));
  },

  methods: {
    metodoMaestroQueSolucionaLaVidaYLaMuerteDeLasEmpresas() {
      
      this.entrKr.nit = this.enterprise.nit;
      this.entrKr.contactMail = this.enterprise.contactMail;
      this.entrKr.contactPhone = this.enterprise.contactPhone;
      this.entrKr.description = this.enterprise.description;
      this.entrKr.name = this.enterprise.name;
    },

    getError (fieldName) {
        return this.errors.first(fieldName)
      },
      validate () {
        this.metodoMaestroQueSolucionaLaVidaYLaMuerteDeLasEmpresas();
        this.$validator.validateAll().then(isValid => {
          if(isValid)
            this.sendEnterprise()
        })
      },
      sendEnterprise() {
      console.log(this.entrKr);
      //this.$set(this.ticket, 'foto', this.images.regular);
      apiMedved.editEnterprise(JSON.stringify(this.entrKr)).then(()=>{this.$router.push("/enterprise/" );});

      
    }
    // sendEnterprise() {
    //   alert(JSON.stringify(this.enterprise));
    // }
  }
};
</script>

<style>
.row .el-select {
  width: 100%;
  margin-bottom: 30px;
}
</style>
