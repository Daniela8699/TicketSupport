<template>
  <div class="row">
    <div class="col-md-7">
      <div class="row">
        <div class="col-sm-4"></div>
      </div>
    </div>
    <div class="card"></div>

    <card style="height: 4rem;">
      <h3
        class="card-title"
      >Synchronization Settings &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>
    </card>

    <div class="row" style="
    width: -webkit-fill-available;">
      <div class="col-md-2"></div>
      <div class="col-md-4">
        <card>
          <h4 slot="header" class="card-title">Start date</h4>
          <base-input
            v-model="model.fechaInicio"
            type="date"
            v-validate="modelValidations.fechaInicio"
            :error="getError('StartDate')"
            name="StartDate"
          >
            <el-date-picker
              v-model="model.fechaInicio"
              type="datetime"
             
              placeholder="Select a starting date"
            ></el-date-picker>
          </base-input>
        </card>
      </div>
      <div class="col-md-4">
        <card>
          <h4 slot="header" class="card-title">Final date</h4>
          <base-input
            v-model="model.fechaFin"
            v-validate="modelValidations.fechaFin"
            :error="getError('FinalDate')"
            name="FinalDate"
          >
            <el-date-picker
              v-model="model.fechaFin"
              type="datetime"
             
              placeholder="Select an end date"
            ></el-date-picker>
          </base-input>
        </card>
      </div>
    </div>

    <div class="row" style="
    width: -webkit-fill-available;">
      <div class="col-md-2"></div>
      <div class="col-md-4">
        <card>
          <h4 slot="header" class="card-title">Automatic sync schedule</h4>
          <base-input
            v-model="model.horario"
            v-validate="modelValidations.horario"
            :error="getError('schedule')"
            name="schedule"
          >
            <el-time-select
              v-model="model.horario"
              :picker-options="{
          start: '00:00',
          step: '00:10',
          end: '23:59'
        }"
              placeholder="Select sync time"
            ></el-time-select>
          </base-input>
          <br />
        </card>
      </div>
      <div class="col-md-4">
        <card>
          <h4 slot="header" class="card-title">Available sites</h4>
          <base-input
            v-model="selects.sitios"
            type="number"
            v-validate="modelValidations.sitios"
            :error="getError('sites')"
            name="sites"
          >
            <el-select
              multiple
              class="select-info"
              collapse-tags
              v-model="selects.sitios"
              placeholder="Sites"
            >
              <el-option
                v-for="option in selects.sites"
                class="select-info"
                :value="option.value"
                :label="option.label"
                :key="option.label"
              ></el-option>
            </el-select>
          </base-input>
        </card>
      </div>
    </div>
    <div class="row" style="
    width: -webkit-fill-available;">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <card>
          <div>
            <div class="row">
              <h4
                class="col-sm-7 col-form-label"
              >Select the times you want to repeat the synchronization per day:</h4>
              <div class="col-sm-5">
                <base-input
                  v-model="selects.cantidad"
                  v-validate="modelValidations.cantidad"
                  :error="getError('repetitions')"
                  name="repetitions"
                >
                  <el-select
                    class="select-info"
                    size="large"
                    placeholder="Select a number "
                    v-model="selects.cantidad"
                  >
                    <el-option
                      v-for="option in selects.quantity"
                      class="select-info"
                      :value="option.value"
                      :label="option.label"
                      :key="option.label"
                    ></el-option>
                  </el-select>
                </base-input>
              </div>
            </div>
          </div>
        </card>
      </div>
    </div>

    <div class="row" style="
    width: -webkit-fill-available;">
      <div class="col-md-4"></div>
      <div class="col-md-4" style="text-align: -webkit-center">
        <base-button type="danger" @click.prevent="validate" v-on:click="loadDB">
          <i class="tim-icons icon-refresh-02"></i>
          Synchronize BD
        </base-button>
      </div>
    </div>
  </div>
</template>







<script >
import swal from "sweetalert2";
import { TimeSelect, DatePicker, Select, Option, Alert } from "element-ui";
import apiMedved from "@/apiMedved.js";
export default {
  name: "configuracion",

  components: {
    [DatePicker.name]: DatePicker,
    [TimeSelect.name]: TimeSelect,
    [Option.name]: Option,
    [Select.name]: Select
  },
  data() {
    return {
      model: {
        fechaInicio: "",
        fechaFin: "",
        horario: ""
      },
      modelValidations: {
        fechaInicio: {
          required: true
        },
        fechaFin: {
          required: true
        },
        horario: {
          required: true
        },
        sitios: {
          required: true
        },
        cantidad: {
          required: true
        }
      },
      selects: {
        cantidad: "",
        quantity: [
          { value: 1, label: "1" },
          { value: 2, label: "2" },
          { value: 3, label: "3" },
          { value: 4, label: "4" },
          { value: 5, label: "5" },
          { value: 6, label: "6" },
          { value: 7, label: "7" },
          { value: 8, label: "8" },
          { value: 9, label: "9" },
          { value: 10, label: "10" },
          { value: 11, label: "11" },
          { value: 12, label: "12" },
          { value: 13, label: "13" },
          { value: 14, label: "14" },
          { value: 16, label: "16" },
          { value: 17, label: "17" },
          { value: 18, label: "18" },
          { value: 19, label: "19" },
          { value: 20, label: "20" },
          { value: 21, label: "21" },
          { value: 22, label: "22" },
          { value: 23, label: "23" }
        ],
        sites: [
          { value: 0, label: "StackOverFlow" },
          { value: 2, label: "ServerFault" },
          { value: 1, label: "AskUbuntu" }
        ],
        sitios: 0
      }
    };
  },

  methods: {
    loadDB() {
      if (
        this.model.fechaInicio != "" &&
        this.model.fechaFin != "" &&
        this.model.horario != "" &&
        this.selects.cantidad != "" &&
        this.selects.sitios != ""
      ) {
        ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        var firstDate = new Date(this.model.fechaInicio);
        var lastDate = new Date(this.model.fechaFin);

        //Metodo para pasar horario a milisegundos
        var dateX = this.model.horario.split(":");
        var hora = parseInt(dateX[0]) * 3600000;
        var minutes = parseInt(dateX[1]) * 60000;
        var dateMilliseconds = hora + minutes;
        var arregloSource = this.selects.sitios;
        var repeat = this.selects.cantidad;

        var menError;
        var menResponse;

        apiMedved
          .initialLoadDB(
            arregloSource[0],
            firstDate.toISOString(),
            lastDate.toISOString()
          )
          .catch(error => 
          this.$notify({
                type: "danger",
                message: "There was an error during the initial load: " + error
              })
          )
          .then(response => {
            if (response.ok == true) {
              this.$notify({
                type: "success",
                message: "The initial load was a success."
              });

              apiMedved
                .loadDB(
                  arregloSource,
                  lastDate.toISOString(),
                  dateMilliseconds,
                  repeat
                )
                .catch(error => console.error("Error:", error))
                .then(response => {
                  if (response.ok == true) {
                    this.$notify({
                      type: "success",
                      message: "The synchronization configuration was a success."
                    });
                  } else {
                    this.$notify({
                      type: "danger",
                      message:
                        "There was an error during the configuration of the synchronization: " + response.statusText
                    });
                  }
                });
            } else {
              this.$notify({
                type: "danger",
                message: "There was an error during the initial load: " + response.statusText
              });
            }
          });
      }
    },

    getError(fieldName) {
      return this.errors.first(fieldName);
    },

    validate() {
      this.$validator.validateAll().then(isValid => {
        this.$emit("on-submit", this.configuracion, isValid);
      });
    }
  }
};
</script>

<style>
</style>