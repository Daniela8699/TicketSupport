<template>
  <div>
    <div class="row">
      <h3>General Charts Reports</h3>
    </div>

    <div class="row ml-auto mr-auto">
      <div class="col-md-5 ml-auto ">
        <card class="card-chart ml-auto mr-auto">
          <template slot="header">
            <h5 class="card-category">Searched Keywords</h5>
            <div class="row">
              <div class="col-sm-6" >
                <h3 class="card-title">
              <i class="tim-icons icon-shape-star text-success"></i> Top 10
            </h3>
              </div>
              
              <div class="col-sm-6" >
                <base-input name="months" v-model="selects.cantidad">
                  <el-select
                 
                    class="select-success"
                    size="large"
                    placeholder="Select a month "
                    v-model="selects.cantidad"
                    @change="updateChartKeywords()"
                  >
                    <el-option
                      v-for="option in selects.quantity"
                      
                      class="select-success"
                      :value="option.value"
                      :label="option.label"
                      :key="option.label"
                    ></el-option>
                  </el-select>
                </base-input>

              </div>
              
            </div>
          </template>
          <div class="chart-area">
            <bar-chart
              :key="componentKey"
              :chart-data="barChart1.chartData"
              :extra-options="barChart1.extraOptions"
              :gradient-colors="barChart1.gradientColors"
              :gradient-stops="barChart1.gradientStops"
              :height="200"
            ></bar-chart>
          </div>
        </card>
      </div>

      <div class="col-md-5 mr-auto">
        <card class="card-chart ml-auto mr-auto" no-footer-line style="
    width: 96%;
">
          <template slot="header">
            <h5 class="card-category">Internal Tickets by company</h5>
            <h3
              class="card-title"
            >Total Internal Tickets: {{this.pieChart2.chartData.datasets[0].totalInternal[0]}}</h3>
         <br><br>
          </template>
          <div class="chart-area">
            
            <bar-chart
              :key="componentKey"
              :chart-data="barChart2.chartData"
              :extra-options="barChart2.extraOptions"
              :height="200"
            ></bar-chart>
          </div>
        </card>
      </div>
    </div>

    <div class="row ml-auto mr-auto">
   
      <card class="card-chart ml-auto card-chart-pie">
        <div class="row ml-auto mr-auto">
          <div class="col-md-5 ml-auto">
            <h3 slot="header">External vs Internal Tickets</h3>
            <div class="chart-area">
              <pie-chart
                :key="componentKey"
                :chart-data="pieChart2.chartData"
                :extra-options="pieChart2.extraOptions"
                :height="120"
              ></pie-chart>
            </div>
          </div>

          <div class="col-xs-6 mr-auto">
            <h3 slot="header">Total Registered</h3>
            <h4 class="card-title">
              <i class="tim-icons icon-tag text-warning"></i>
              Total logs: {{this.pieChart2.chartData.datasets[0].totalLogs[0]}}
              <br />
              <i class="tim-icons icon-tag text-warning"></i>
              Total Internal Tickets: {{this.pieChart2.chartData.datasets[0].totalInternal[0]}}
              <br />
              <i class="tim-icons icon-tag text-warning"></i>
              Total External Tickets: {{this.pieChart2.chartData.datasets[0].totalExternal[0]}}
              <br />
              <i class="tim-icons icon-tag text-warning"></i>
              Total Enterprises: {{this.pieChart2.chartData.datasets[0].totalEnter[0]}}
            </h4>

            <p class="category">A total of registered in database</p>
          </div>
        </div>
      </card>
      
    </div>

    <div class="row ml-auto mr-auto">
      <card class="card-chart2 ml-auto card-chart-pie">
        <template slot="header">
          <h5 class="card-category">Created internal tickets per month</h5>
          <h3 class="card-title">
            <i class="tim-icons icon-send text-info"></i> Internal tickets created in 2020
          </h3>
        </template>
        <div class="chart-area">
          <line-chart
            :key="componentKey"
            :labels="lineChart2.labels"
            :chart-data="lineChart2.chartData"
            :extra-options="lineChart2.extraOptions"
            :height="200"
          ></line-chart>
        </div>
      </card>
    </div>

     <div class="row ml-auto mr-auto">
      <card class="card-chart2 ml-auto card-chart-pie">
        <template slot="header">
          <h5 class="card-category">Closed internal tickets per month</h5>
          <h3 class="card-title">
            <i class="tim-icons icon-send text-info"></i> Internal tickets closed in 2020
          </h3>
        </template>
        <div class="chart-area">
          <line-chart
            :key="componentKey"
            :labels="lineChart1.labels"
            :chart-data="lineChart1.chartData"
            :extra-options="lineChart1.extraOptions"
            :height="200"
          ></line-chart>
        </div>
      </card>
    </div>
  </div>
</template>
<script>
import LineChart from "src/components/Charts/LineChart";
import BarChart from "src/components/Charts/BarChart";
import PieChart from "src/components/Charts/PieChart";
import config from "@/config";
import * as chartConfigs from "@/components/Charts/config";
import apiMedved from "src/apiMedved.js";
import { Select, Option} from "element-ui";

export default {
  components: {
    [Option.name]: Option,
    [Select.name]: Select,
    LineChart,
    BarChart,
    PieChart
  },
  data() {
    return {
      selects: {
        cantidad: "",
        quantity: [
          { value: 1, label: "January" },
          { value: 2, label: "February" },
          { value: 3, label: "March" },
          { value: 4, label: "April" },
          { value: 5, label: "May" }
        ]
      },

      componentKey: 0,

       lineChart1: {
        chartData: {
          labels: [   
            "ENE",
            "FEB",
            "MAR",
            "ABR",
            "MAY",
            "JUN",
            "JUL",
            "AUG",
            "SEP",
            "OCT",
            "NOV",
            "DEC"
            ],
          datasets: [
            {
              label: 'InternalTickets',
              fill: true,
              borderColor: config.colors.primary,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              pointBackgroundColor: config.colors.primary,
              pointBorderColor: 'rgba(255,255,255,0)',
              pointHoverBackgroundColor: '#be55ed',
              pointBorderWidth: 20,
              pointHoverRadius: 4,
              pointHoverBorderWidth: 15,
              pointRadius: 4,
              data: []
            }
          ]
        },
        extraOptions: chartConfigs.purpleChartOptions,
        gradientColors: config.colors.primaryGradient,
        gradientStops: [1, 0.4, 0]
      },

      lineChart2: {
        chartData: {
          labels: [
            "ENE",
            "FEB",
            "MAR",
            "ABR",
            "MAY",
            "JUN",
            "JUL",
            "AUG",
            "SEP",
            "OCT",
            "NOV",
            "DEC"
          ],
          datasets: [
            {
              label: "InternalTickets",
              fill: true,
              borderColor: config.colors.info,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              pointBackgroundColor: config.colors.info,
              pointBorderColor: "rgba(255,255,255,0)",
              pointHoverBackgroundColor: "#2380f7",
              pointBorderWidth: 20,
              pointHoverRadius: 4,
              pointHoverBorderWidth: 15,
              pointRadius: 4,
              data: []
            }
          ]
        },
        extraOptions: chartConfigs.lineChartOptionsBlue
      },

      barChart1: {
        chartData: {
          labels: [],
          datasets: [
            {
              label: "Data",
              fill: true,
              borderColor: config.colors.primary,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              data: []
            }
          ]
        },
        extraOptions: chartConfigs.barChartOptionsGradient,
        gradientColors: config.colors.primaryGradient,
        gradientStops: [1, 0.4, 0]
      },
      barChart2: {
        chartData: {
          labels: [],
          initial: [][2],
          datasets: [
            {
              label: "Internal Tickets",
              fill: true,
              backgroundColor: config.colors.info,
              hoverBackgroundColor: config.colors.info,
              borderColor: config.colors.info,
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              data: []
            }
          ]
        },
        extraOptions: chartConfigs.barChartOptionsGradient
      },

      pieChart2: {
        chartData: {
          labels: [],
          datasets: [
            {
              label: "Data",
              pointRadius: 0,
              pointHoverRadius: 0,
              backgroundColor: ["#ff8779", "#2a84e9", "#e2e2e2"],
              borderWidth: 0,
              data: [],
              totalLogs: [],
              totalInternal: [],
              totalExternal: [],
              totalEnter: []
            }
          ]
        },
        extraOptions: chartConfigs.pieChartOptions
      },

      tableData: []
    };
  },

  methods: {
    forceRerender() {
      this.componentKey += 1;
    },
    internalsList() {},
    data1() {
      var logs;
      logs = this.tableData[0];
      return logs;
    },
    updateChartKeywords(){
      var words;
      this.barChart1.chartData.labels=[]
      this.barChart1.chartData.datasets[0].data=[]
      apiMedved.getTopKeywords(this.selects.cantidad).then(response => {
        this.words = response.keywords;
        JSON.stringify(this.words);
        var that = this;
        this.words.forEach(element => {
          that.barChart1.chartData.labels.push(element.word);
          that.barChart1.chartData.datasets[0].data.push(element.frequency);
        });
        this.forceRerender();
      });
    }
  },
  created() {
      var words;

    this.selects.cantidad=1
    apiMedved.getTopKeywords(this.selects.cantidad).then(response => {
      this.words = response.keywords;
      JSON.stringify(this.words);
      var that = this;
      this.words.forEach(element => {
        that.barChart1.chartData.labels.push(element.word);
        that.barChart1.chartData.datasets[0].data.push(element.frequency);
      });
      this.forceRerender();
    });

    apiMedved.getCountSynchReports().then(rep => {
      this.pieChart2.chartData.datasets[0].totalLogs.push(rep);
      this.forceRerender();
    });

    apiMedved.getCountInternalTickets().then(int => {
      this.pieChart2.chartData.labels.push("InternalTck");
      this.pieChart2.chartData.datasets[0].data.push(int);
      this.pieChart2.chartData.datasets[0].totalInternal.push(int);
      this.forceRerender();
    });

    apiMedved.getCountEnterprises().then(ent => {
      this.pieChart2.chartData.datasets[0].totalEnter.push(ent);
      this.forceRerender();
    });

    apiMedved.getCountExternalTickets().then(ext => {
      this.pieChart2.chartData.labels.push("ExternalTck");
      this.pieChart2.chartData.datasets[0].data.push(ext);
      this.pieChart2.chartData.datasets[0].totalExternal.push(ext);
      this.forceRerender();
    });

    apiMedved.getInternalPerEnterprise().then(enter => {
      this.barChart2.chartData.initial = enter;
      for (
        let index = 0;
        index < this.barChart2.chartData.initial.length;
        index++
      ) {
        this.barChart2.chartData.labels.push(
          this.barChart2.chartData.initial[index][0]
        );
        this.barChart2.chartData.datasets[0].data.push(
          this.barChart2.chartData.initial[index][1]
        );
      }
      this.forceRerender();
    });

    ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    var firstDate = new Date("2020-01-01 00:00:00");
    var lastDate = new Date("2020-12-31 00:00:00");

    apiMedved
      .getCreatedInternalPerMonth(
        firstDate.toISOString(),
        lastDate.toISOString()
      )
      .then(inter => {
        this.lineChart2.chartData.datasets[0].data = inter;
        this.forceRerender();
      });

   
    apiMedved
    .getClosedInternalPerMonth(
      firstDate.toISOString(),
      lastDate.toISOString()
    )
    .then(intercl => {
        this.lineChart1.chartData.datasets[0].data = intercl;
        this.forceRerender();
      });


  }
};
</script>
<style>
/*.card-chart2 {
  width: 81%;
  margin-left: 71px;
}
.col-xs-6 {
  margin-top: 30px;
  margin-bottom: 40px;
}
.col-xs-12 {
  margin-left: 60px;
  margin-top: 30px;
  margin-bottom: 40px;
}*/
</style>

