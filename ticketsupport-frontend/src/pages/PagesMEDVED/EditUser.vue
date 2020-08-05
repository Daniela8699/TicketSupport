
<template>
  <div class="row">
    <div class="col-md-6">
      <card class="stacked-form" title="Stacked Form">
        <h4 slot="header" class="card-title">Edit User</h4>
        <form @submit.prevent>
          <div>
            <base-input label="Username"
              type="text"
              placeholder="Username"
              name="Username"
              v-validate='modelValidations.username'
              :error="getError('Username')"
              required
              :readonly=true
              v-model="user.username"></base-input>

            <base-input
              label="Name"
              type="text"
              placeholder="Name"
              required
              name="name"
              v-validate='modelValidations.name'
              :error="getError('name')"
              v-model="user.name"
            ></base-input>
            
            <base-input
              label="lastName"
              type="text"
              placeholder="Last Name"
              required
              name="lastName"
              v-validate='modelValidations.lastName'
              :error="getError('LastName')"
              v-model="user.lastName"
            ></base-input>

            <base-input
              label="password"
              type="password"
              placeholder="Password"
              required
              name="password"
              v-validate='modelValidations.password'
              :error="getError('password')"
              v-model="user.password"
            ></base-input>

            <base-input
              label="Mail contact"
              type="email"
              placeholder="example@example.com"
              required
              name="mail"
              v-validate='modelValidations.email'
              :error="getError('mail')"
              v-model="user.email"
            ></base-input>

            

            <h4 class="card-title">Assign enterprise</h4>
            <el-select
              multiple
              multiple-limit="1"
              class="select-primary"
              collapse-tags
              size="large"
              placeholder="Select Enterprise"
              v-model="enterpriseNit"
            >
              <el-option
                v-for="option in enterprises"
                class="select-primary"
                :value="option.nit"
                :label="option.name"
                :key="option.nit"
              ></el-option>
            </el-select>

             <h4 class="card-title">Assign Rol</h4>
            <el-select
              multiple
              multiple-limit="1"
              class="select-primary"
              collapse-tags
              size="large"
              placeholder="Select rol"
              v-model="rolID"
            >
              <el-option
                v-for="option in rols"
                class="select-primary"
                :value="option.id"
                :label="option.name"
                :key="option.id"
              ></el-option>
            </el-select>

            <base-button
              class="mt-3"
              native-type="submit"
              @click.prevent="editUser"
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
import swal from 'sweetalert2';

import apiMedved from "src/apiMedved.js";

export default {
  components: {
    [Option.name]: Option,
    [Select.name]: Select
  },

  data() {
    return {
      user: null,
      enterpriseNit : [],
      rolID : [],
      modelValidations: {
        username: {
          required: true,
        },
        name: {
          required: true,
        },
        lastName: {
          required:true
        },
        email: {
          email:true,
          required:true
        },
        password: {
          required:true,
        },
        enterpriseNit:{
            required:true,
        },
        rolID:{
            required:true,
        }
      },

      enterprises:[],
      rols:[],
    };
  },
  created() {
     apiMedved
      .getAllEnterprises()
      .then(enters => (this.enterprises= enters));

      apiMedved
      .getAllRol()
      .then(ans => (this.rols= ans));

      apiMedved
      .getUser(this.$route.params.id)
      .then(ans => {
         this.user = ans;
         this.enterpriseNit.push(this.user.enterprises.nit);
         this.rolID.push(this.user.rols[0].id)
      });
  },

  methods: {

    getError (fieldName) {
        return this.errors.first(fieldName)
      },
      validate () {
        this.$validator.validateAll().then(isValid => {
          if(isValid){
            let errMsg = ""
            if(this.user.enterpriseNit.length == 0)
            {
              errMsg = "Enterprise is required \n"
            }

            if(this.user.rolID.length == 0)
            {
              errMsg = errMsg + "Rol is required \n"
            }
            
            if(errMsg == "")
            {
               apiMedved.editUser(JSON.stringify(this.user)).then(()=>{this.$router.push("/users/" )});
            }else{
              swal({
                title: errMsg,
                buttonsStyling: false,
                type: 'error',
                confirmButtonClass: 'btn btn-error btn-fill'
              });
            }
            }        })
      },
    editUser() {
    //   //this.$set(this.ticket, 'foto', this.images.regular);
      this.user.rolID = this.rolID;
      this.user.enterpriseNit = this.enterpriseNit;
      this.validate();
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
