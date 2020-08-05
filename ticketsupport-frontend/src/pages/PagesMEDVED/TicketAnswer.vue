<template>

  <section>
    
    <div id="answerWrapper">

      <div id="extraInfo">
        <p>
          <i class="material-icons">calendar_today</i>
          <br>
          {{answer.creation_date.split("T")[0]}}

        </p>
        <p v-if="answer.is_accepted === true">
          <i class="material-icons">check_circle_outline</i>
        </p>
      </div>
      <div id="answerBody" v-html="answer.body"></div>
    </div>
  </section>
</template>

<script >
import Vue from "vue";
export default {
  props: ["answer"]
};
</script>

<style scoped>
#answerBody >>> pre{
  background:#25253A;
  border-radius: 5px;
  padding: 10px;
  
}

#answerWrapper {
  border-top: #2a4787 solid 0.1px;
  display: grid;
  
  grid-template-columns: 5% 85%;
  /* grid-template-areas:
    "extraInfo answerBody "
    "extraInfo answerBody "; */
}
#answerBody {
  /* grid-area: answerBody; */
  padding: 10px;
  margin: 5px;
  color:white;
}
#extraInfo {
  /* grid-area: extraInfo; */
  display: block;
}
#extraInfo * {
  /* grid-area: extraInfo; */
  text-align: center;
  padding-top: 10px;
  padding-bottom: 10px;
}
/* #extraInfo p {
  margin-left: 20px;
  margin-right: 10px;
} */
section {
  padding-left: 10px;
}
</style>