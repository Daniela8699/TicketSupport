<template>
<div id="divTicket">
  <section>
    <head>
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    </head>
    <h1 id="ticketTitle">{{ticket.ticket.title}}</h1>
    <p id="ticketId">id: {{ticket.ticket.id}}</p>
    <div id="ticketIsAnswered">
      <p>Is Answered &nbsp;</p>
      <i v-if=" ticket.ticket.answered === true " class="material-icons md-light">check</i>
      <i v-else-if=" ticket.ticket.answered === false" class="material-icons md-light" style="margin-bottom=-5%">close</i>
    </div>
    <div id="ticketDateInfo">
       <p v-if=" ticket.ticket.openDate === null" id="ticketOpenDate">Open date: &nbsp; &nbsp; &nbsp; </p>
      <p v-if=" ticket.ticket.openDate !== null" id="ticketOpenDate">Open date: &nbsp; &nbsp; &nbsp; {{formatDate(ticket.ticket.openDate)}}</p>
      <p v-if=" ticket.ticket.closeDate === null" id="ticketCloseDate">Close date: &nbsp; &nbsp; &nbsp;</p>
      <p v-if=" ticket.ticket.closeDate !== null" id="ticketCloseDate">Close date: &nbsp; &nbsp; &nbsp;{{formatDate(ticket.ticket.closeDate)}}</p>
       <p  v-if=" ticket.ticket.lastActivityDate === null" id="ticketLastActivity" >Last activity: &nbsp; &nbsp; &nbsp;</p>
      <p v-if=" ticket.ticket.lastActivityDate !== null" id="ticketLastActivity" >Last activity: &nbsp; &nbsp; &nbsp;{{formatDate(ticket.ticket.lastActivityDate)}}</p>
    </div>
    <div id="ticketDescription" v-html="ticket.ticket.description"></div>
  </section>

  <section id="aditionalInfo" v-if="ticket.hasOwnProperty('prioridad')">
    <p>Priority: {{ticket.prioridad.nombre}}</p>
    <p>State: {{ticket.ticketState.stateName}}</p>
    <p>Creator: {{ticket.userCreator.name}} {{ticket.userCreator.lastName}}</p>
    <p>Enterprise: {{ticket.enterprise.name}}</p>
    <p>Contact Phone: {{ticket.enterprise.contactPhone}}</p>
    <p>Contact Email: {{ticket.enterprise.contactMail}}</p>
  </section>
  <section id="aditionalInfo" v-if="ticket.hasOwnProperty('link')">
    <p>Score: {{ticket.score}}</p>
    <p>
      link:
      <a target="_blank" rel="noopener noreferrer" :href="ticket.link">{{ticket.link}}</a>
    </p>
  </section>
</div>
</template>

<script lang="ts">
import Vue from "vue";

export default {
  props: ["ticket"],
   methods: {
     formatDate(time){
     

        var t = new Date(time)

       return t.toLocaleString("sv-SE")
      }
   }
};

</script>

<style scoped>

.material-icons.md-light {
  color: rgba(255, 255, 255, 1);
}
body {
  color: white;
}
#divTicket{
  display: grid;
  grid-template-areas:
    "title         title      title"
    "id            id         id"
    "isAnswered    isAnswered isAnswered"
    "ticketDateInfo ticketDateInfo ticketDateInfo"
    "description   description description"
    "aditionalInfo   aditionalInfo aditionalInfo";
}
 #ticketDescription >>> pre{
  background:#25253A;
  border-radius: 5px;

  
}

#ticketTitle {
  grid-area: title;
  margin-bottom: 10px;
}
#ticketId {
  grid-area: id;
}
#ticketIsAnswered {
  grid-area: isAnswered;

  display: flex;
}
#ticketDateInfo {
  grid-area: ticketDateInfo;
  display: flex;
}
#ticketDateInfo p {
  font-size: 0.8em;
  padding-right: 2em;
}
#ticketDescription {
  grid-area: description;
  font-size: 1.1em;
}
#aditionalInfo{
  grid-area:aditionalInfo ;
  display: flex;
}
#aditionalInfo p{
  padding-right: 2em;
}
</style>
