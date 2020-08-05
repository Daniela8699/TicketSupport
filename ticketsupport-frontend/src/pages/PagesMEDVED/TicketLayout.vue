<template>
  <section v-if="tick">
    <div v-if="tick" id="ticketWrapper">

      <Ticket class="ticketChild" v-bind:ticket="tick"></Ticket>
              <h2> Answers</h2>
      <TicketAnswer class="ticketChild" v-for="a in answers" v-bind:key="a.id" v-bind:answer="a"></TicketAnswer>
    </div>
  </section>
</template>

<script>
import Ticket from "./Ticket";
import TicketAnswer from "./TicketAnswer";
import apiMedved from "@/apiMedved";
export default {
  components: {
    Ticket,
    TicketAnswer
  },
  data() {
    return {
      tick: null,
      answers: []
    };
  },
  methods: {
    setParameters(Tick) {
      this.tick = Tick;
      this.answers = this.tick.ticket.answers.sort((a,b)=>{ return a.is_accepted?-1:1});
    },
    getRoute() {
      return this.$route.params.id;
    }
  },
  created() {
    apiMedved
      .getExternalTicket(this.getRoute())
      .then(tkt => this.setParameters(tkt))
      .catch(
        apiMedved
          .getInternalTicket(this.getRoute())
          .then(tkt => this.setParameters(tkt))
      );
  }
};
</script>
<style scoped >
section{
  position: relative;
  /* height: 70vh; */
  /* overflow: auto; */
  
}

#ticketWrapper {
  color: white;
  font-size: 12px;
  padding-left: 5px;
  padding: 10px;
  text-align: justify;
  border-width: 0.5px;
  position: absolute;
}

</style>
