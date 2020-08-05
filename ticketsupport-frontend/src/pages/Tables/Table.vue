<template>
  <div>
    <card>
      <table class="table">
        <thead>
          <tr>
            <th
              class="text-center"
              v-for="item in headsObjetive"
              v-bind:key="item.title"
            >{{item.title}}</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <div class="d-flex justify-content-center">
                <el-select
                  v-validate="'required'"
                  :error="getError('myinput')"
                  name="myinput"
                  class="select-danger"
                  placeholder="Type objetive"
                  v-model="selectTypeObjetive.simple"
                >
                  <el-option
                    v-for="option in selectTypeObjetive.types"
                    class="select-danger"
                    :value="option.value"
                    :label="option.label"
                    :key="option.label"
                  ></el-option>
                </el-select>
              </div>
            </td>
            <td v-for="n in elements" :key="n" class="text-center">
              <div class="align-self-center">
                <base-input
                  type="number"
                  v-model="variablesObjetive[n]"
                  v-validate="'required'"
                  :error="getError('X'+ n)"
                  v-bind:name="'X'+ n"
                ></base-input>
              </div>
            </td>
            <td v-if="editableVariables">
              <div class="d-flex justify-content-center">
                <button
                  v-on:click="responseAddVariable"
                  class="btn btn-primary btn-fab btn-icon btn-round"
                >
                  <i class="tim-icons icon-simple-add"></i>
                </button>
                <button
                  v-on:click="responseRemoveVariable"
                  class="btn btn-primary btn-fab btn-icon btn-round"
                >
                  <i class="tim-icons icon-simple-delete"></i>
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </card>
    <card>
      <table class="table">
        <thead>
          <tr>
            <th class="text-center" v-for="item in headsType" v-bind:key="item.title">{{item.title}}</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td v-for="n in elements" :key="n">
              <div class="d-flex justify-content-center">
                <el-select
                  class="select-danger"
                  placeholder="Type Variable"
                  v-model="selectTypeType.simple[n]"
                >
                  <el-option
                    v-for="option in selectTypeType.types"
                    class="select-danger"
                    :value="option.value"
                    :label="option.label"
                    :key="option.label"
                  ></el-option>
                </el-select>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </card>
    <card>
      <table class="table">
        <thead>
          <tr>
            <th
              class="text-center"
              v-for="item in headsContraint"
              v-bind:key="item.title"
            >{{item.title}}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="m in constraints" :key="m">
            <td v-for="n in elements" :key="n">
              <base-input
                type="number"
                v-model="variables[m][n]"
                v-validate="'required'"
                :error="getError('X'+ m +'-'+ n)"
                v-bind:name="'X'+ m +'-'+ n"
              ></base-input>
            </td>
            <td>
              <div class="d-flex justify-content-center"> 

              <el-select
                class="select-danger"
                placeholder="Type Constraint"
                v-model="selectTypeConstraint.simple[m]"
              >
                <el-option
                  v-for="option in selectTypeConstraint.types"
                  class="select-danger"
                  :value="option.value"
                  :label="option.label"
                  :key="option.label"
                ></el-option>
              </el-select>
              </div>
            </td>
            <td>
              <base-input
                type="number"
                v-model="values[m]"
                v-validate="'required'"
                :error="getError('Value '+ m)"
                v-bind:name="'Value '+ m"
              ></base-input>
            </td>
          </tr>
          <tr>
            <td :colspan="elements+2">
            <div class="d-flex justify-content-center">
            <button
              v-on:click="responseAddConstraint"
              class="btn btn-primary btn-fab btn-icon btn-round"
            >
              <i class="tim-icons icon-simple-add"></i>
            </button>
            <button
              v-on:click="responseRemoveConstraint"
              class="btn btn-primary btn-fab btn-icon btn-round"
            >
              <i class="tim-icons icon-simple-delete"></i>
            </button>
            </div>
            </td>
          </tr>
        </tbody>

      </table>
        <button @click="constructQueryString">holiwis</button>
    </card>
    {{this.selectTypeObjetive.simple}}
    {{this.selectTypeType.simple}}
    {{this.selectTypeConstraint.simple}}
    {{this.elements}}
    {{this.variablesObjetive}}
    {{this.variables}}
    {{this.values}}
  </div>
</template>

<script>
import { Select, Option } from "element-ui";

export default {
  components: {
    [Select.name]: Select,
    [Option.name]: Option
  },
  data: function() {
    return {
      selectTypeObjetive: {
        simple: "MAXIMIZE",
        types: [
          { value: "MAXIMIZE", label: "MAXIMIZE" },
          { value: "MINIMIZE", label: "MINIMIZE" }
        ]
      },
      selectTypeType: {
        simple: [],
        types: [
          { value: "CONTINUOUS", label: "CONTINUOUS" },
          { value: "INTEGER", label: "INTEGER" },
          { value: "BINARY", label: "BINARY" }
        ]
      },
      selectTypeConstraint: {
        simple: [],
        types: [
          { value: "<=", label: "<=" },
          { value: ">=", label: ">=" },
          { value: "=", label: "=" }
        ]
      },
      headsObjetive: [
        { title: "Type" },
        { title: "X1" },
        { title: "X2" },
        { title: "ACTIONS" }
      ],
      headsType: [{ title: "X1" }, { title: "X2" }],
      headsContraint: [
        { title: "X1" },
        { title: "X2" },
        { title: "TYPE" },
        { title: "VALUE" }
      ],
      elements: 2,
      constraints: 1,
      variablesObjetive: [],
      variables: [[], []],
      values: [],
      editableVariables: true
    };
  },

  methods: {
    getError(fieldName) {
      return this.errors.first(fieldName);
    },

    validate() {
      alert("claudita");
      this.$validator.validateAll().then(isValid => {
        this.$emit("on-submit", this.registerForm, isValid);
        if (isValid) {
          alert("valid");
        } else {
          alert("invalid");
        }
      });
    },
    responseAddVariable: function(event) {
      this.headsObjetive.splice(this.elements + 1, 1);
      this.headsContraint.splice(this.elements + 1, 1);
      this.headsContraint.splice(this.elements, 1);
      this.elements += 1;
      this.headsObjetive.push({ title: "X" + this.elements });
      this.headsObjetive.push({ title: "ACTIONS" });
      this.headsType.push({ title: "X" + this.elements });
      this.headsContraint.push({ title: "X" + this.elements });
      this.headsContraint.push({ title: "TYPE" });
      this.headsContraint.push({ title: "VALUE" });
    },
    responseRemoveVariable: function(event) {
      if (this.elements > 2) {
        this.headsObjetive.splice(this.elements + 1, 1);
        this.headsObjetive.splice(this.elements, 1);
        this.headsType.splice(this.elements - 1, 1);
        this.headsContraint.splice(this.elements + 1, 1);
        this.headsContraint.splice(this.elements, 1);
        this.headsContraint.splice(this.elements - 1, 1);
        this.selectTypeType.simple.splice(this.elements, 1);
        this.variablesObjetive.splice(this.elements, 1);
        var i;
        for (i = 0; i < this.constraints + 1; i++) {
          this.variables[i].splice(this.elements, 1);
        }
        this.elements -= 1;
        this.headsObjetive.push({ title: "ACTIONS" });
        this.headsContraint.push({ title: "TYPE" });
        this.headsContraint.push({ title: "VALUE" });
      }
    },
    responseAddConstraint: function(event) {
      this.constraints += 1;
      this.variables.push([]);
    },
    responseRemoveConstraint: function(event) {
      this.values.splice(this.constraints, 1);
      this.selectTypeConstraint.simple.splice(this.constraints, 1);
      this.variables.splice(this.constraints, 1);
      this.constraints -= 1;
    },
    constructQueryString: function(event) {
      this.validate();
      var retorno = "type=";
      // type=MAXIMIZE&vars=X1:I,X2:B,X3:I&objectiveFunction=55,99,11&constraints=66,6,6,<=,6;6,6,6,<=,2;
      retorno += this.selectTypeObjetive.simple + "&vars=";
      var i;
      for (i = 0; i < this.elements; i++) {
        var type = "";
        var getType = this.selectTypeType.simple[i + 1];
        if (getType == "CONTINUOUS") type = "C";
        else if (getType == "INTEGER") type = "I";
        else type = "B";
        retorno += "X" + (i + 1) + ":" + type;
        if (i + 1 < this.elements) {
          retorno += ",";
        }
      }
      retorno += "&objectiveFunction=";
      i = 0;
      for (i = 0; i < this.elements; i++) {
        retorno += this.variablesObjetive[i + 1];
        if (i + 1 < this.elements) {
          retorno += ",";
        }
      }
      retorno += "&constraints=";
      i = 0;
      for (i = 0; i < this.constraints; i++) {
        var j = 0;
        for (j = 0; j < this.elements; j++) {
          retorno += this.variables[i + 1][j + 1] + ",";
        }
        retorno += this.selectTypeConstraint.simple[i + 1] + ",";
        retorno += this.values[i + 1] + ";";
      }
      alert(retorno);
      return retorno;
    }
  }
};
</script>
<style>
</style>