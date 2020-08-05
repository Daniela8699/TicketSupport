
<template>
  <div class="row">
    <div class="col-md-6">
      <card class="stacked-form" title="Stacked Form">
        <h4 slot="header" class="card-title">New Enterprise</h4>
        <form @submit.prevent>
          <div>
            <base-input label="NIT"
              type="text"
              placeholder="NIT"
              name="NIT"
              v-validate='modelValidations.NIT'
              :error="getError('NIT')"
              required
              v-model="enterprise.nit"></base-input>

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
              placeholder="### ### ###"
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
              v-model="enterprise.sources"
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
              class="mt-3"
              native-type="submit"
              @click.prevent="saveNewEnterprise"
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

import apiMedved from "src/apiMedved.js";

export default {
  components: {
    [Option.name]: Option,
    [Select.name]: Select
  },

  data() {
    return {
      enterprise: {
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

      users: null,

      sources: null
    };
  },
  created() {
     apiMedved
      .getAllSources()
      .then(sour => (this.sources = sour));

  apiMedved
      .getAllUsers()
      .then(us => (this.users = us));
  },

  methods: {

    getError (fieldName) {
        return this.errors.first(fieldName)
      },
      validate () {
        this.$validator.validateAll().then(isValid => {
          alert(isValid ? JSON.stringify(this.enterprise) : 'Not Valid');
        })
      },
    // sendEnterprise() {
    //   alert(JSON.stringify(this.enterprise));
    // }
    saveNewEnterprise() {
      //this.$set(this.ticket, 'foto', this.images.regular);
      apiMedved.saveNewEnterprise(JSON.stringify(this.enterprise)).then(()=>{this.$router.push("/enterprise/" )});
      
      
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
