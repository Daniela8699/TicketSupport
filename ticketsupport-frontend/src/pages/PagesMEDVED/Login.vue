<template>
  <div class="container">
    <div class="col-lg-4 col-md-6 ml-auto mr-auto">
      <form @submit.prevent="login">
        <card
  type="pricing"
  class="card-danger card-white"
  footer-classes="text-center mb-3 mt-3"
>
  <h1 class="card-title">Log in</h1>
  <img class="card-img" src="https://demos.creative-tim.com/vue-black-dashboard-pro/img/card-danger.png" alt="Image" />
 <br>
 <br>
 <br>
 <br>
 <br>
 <div class="col-md-12">
   
            <base-input
        
              name="username"
              
              v-model="model.username"
              placeholder="username"
              addon-left-icon="tim-icons icon-email-85"
            >
            </base-input>

            <base-input
              v-validate="'required|min:1'"
              name="password"
              :error="getError('password')"
              v-model="model.password"
              type="password"
              placeholder="Password"
              addon-left-icon="tim-icons icon-lock-circle"
            >
            </base-input>
          </div>

  <div slot="footer">
            <base-button v-on:click="login" native-type="submit" type="danger" class="mb-3" size="lg" block>
              Log in
            </base-button>
          </div>
</card>
      </form>
    </div>
  </div>
</template>
<script>

import apiMedved from "src/apiMedved.js";

export default {
  data() {
    return {
      model: {
          // revisar los nombres de esto en el modelo
        username: '',
        password: '',
      },
      token: '',
    };
  },
 created(){
     if(localStorage.getItem('Token') !== null){
       localStorage.removeItem('Token');
     }
  },
  methods: {
    getError(fieldName) {
      return this.errors.first(fieldName);
    },
    async login() {
      let isValidForm = await this.$validator.validateAll();
      if (isValidForm) {
          this.token = await apiMedved.mostCrackValidationMethodInExistanceUpToDate(JSON.stringify(this.model))
          localStorage.setItem("Token", this.token);

        if(localStorage.getItem('Token').toString().localeCompare("Bearer Invalid")==0){
          localStorage.removeItem('Token');
          alert("Your information is invalid please try again");
          
        }
        else if(localStorage.getItem('Token').toString().localeCompare("Bearer undefined")==0){
          localStorage.removeItem('Token');
          alert("Error on backend, token not generated");
        }
          else{
           this.$router.push("/Users/");
        };

      

      }
    }
  }
};
</script>
<style>
.navbar-nav .nav-item p {
  line-height: inherit;
  margin-left: 5px;
}
</style>
