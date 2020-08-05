import Vue from 'vue';
import Router from 'vue-router';
import DashboardLayout from './../pages/Starter/SampleLayout.vue';
import Tickets from './../pages/PagesMEDVED/Tickets.vue';
import Reports from './../pages/PagesMEDVED/Reports.vue';
import ChartsReports from './../pages/PagesMEDVED/ChartsReports.vue';
import Users from './../pages/PagesMEDVED/Users.vue';
import Enterprise from './../pages/PagesMEDVED/Enterprise.vue';
import Search from './../pages/PagesMEDVED/Search.vue';
import Configuration from './../pages/PagesMEDVED/Configuration.vue';
import TicketLayout from '../pages/PagesMEDVED/TicketLayout.vue';
import AddTickets from '../pages/PagesMEDVED/AddTickets.vue';
import EditTickets from '../pages/PagesMEDVED/EditTickets.vue';
import AnswerTicket from '../pages/PagesMEDVED/AnswerTicket.vue';
import AddEnterprise from '../pages/PagesMEDVED/AddEnterprise.vue';
import EditEnterprise from '../pages/PagesMEDVED/EditEnterprise.vue';
import AddUser from '../pages/PagesMEDVED/AddUser.vue'
import Login from '../pages/PagesMEDVED/Login.vue';
import EditUser from '../pages/PagesMEDVED/EditUser.vue';
import AuthLayout from 'src/pages/Pages/AuthLayout.vue';

Vue.use(Router);
let authPages = {
  path: '/',
  component: AuthLayout,
  name: 'Authentication',
  children: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]}

export default new Router({
  mode : 'history',
  base:'/medved/',
  redirect: '/medved/' ,
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: '/login',
      component: AuthLayout,
    children:[
      {
        path: '/login',
        name: 'Login',
        components: { default: Login }
      }
    ]},{
        path: '/',
      name: 'tickets',
      redirect: '/tickets',
      component: DashboardLayout,
      children: [

        {
          path: '/tickets',
          name: 'Tickets',
          components: { default: Tickets }
        },
        ,
        {
          path: 'ticket/:id',
          name: 'Ticket',
          components: { default: TicketLayout }
        },
        {
          path: 'reports',
          name: 'reports',
          components: { default: Reports }

        },
        {
          path: 'chartsReports',
          name: 'Reports',
          components: { default: ChartsReports }

        },
        {
          path: 'newTicket',
          name: 'Ticket',
          components: { default: AddTickets }

        },
        {
          path: 'editTicket/:id',
          name: 'Edit Ticket',
          components: { default: EditTickets }
        },
        {
          path: 'answer/:id',
          name: 'Answer Ticket',
          components: { default: AnswerTicket }
        },
        {
          path: 'search',
          name: 'search',
          components: { default: Search }

        },
        {
          path: 'users',
          name: 'users',
          components: { default: Users }

        },
        {
          path: 'enterprise',
          name: 'enterprise',
          components: { default: Enterprise }

        },
        {
          path: 'configuration',
          name: 'configuration',
          components: { default: Configuration }

        },
        {
          path: 'newEnterprise',
          name: 'new Enterprise',
          components: { default: AddEnterprise}
        },
        {
          path: 'editEnterprise/:nit',
          name: 'new Enterprise',
          components: { default: EditEnterprise}
        },
        {
          path: 'addUser',
          name: 'Add Agent',
          components: { default: AddUser}
        },
        {
          path: 'editUser/:id',
          name: 'Edit Agent',
          components: { default: EditUser}
        },
      ]
    }
  
  ],
  linkActiveClass: 'active',
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  }
});
