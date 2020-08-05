<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <notifications></notifications>
    <sidebar-fixed-toggle-button />
    <side-bar
      :background-color="sidebarBackground"
      short-title="MV"
      title="MEDVED S.A.S."
    >
      <template  slot="links">
        
        <sidebar-item
          :link="{ name: $t('Tickets'), icon: 'tim-icons icon-notes' }"
        >
        <sidebar-item
          :link="{
            name: $t('Tickets Info'),
            icon: 'tim-icons icon-notes',
            path: '/tickets'
          }"
        >
        </sidebar-item>
          <sidebar-item
          :link="{
            name: $t('New Ticket'),
            icon: 'tim-icons icon-simple-add',
            path: '/newTicket'
          }"
        >
         </sidebar-item>
        </sidebar-item>
        <sidebar-item
          :link="{
            name: $t('Reports'),
            icon: 'tim-icons icon-book-bookmark'
      
          }"
        >
        <sidebar-item
          :link="{
            name: $t('Reports'),
            icon: 'tim-icons icon-notes',
            path: '/reports'
          }"
        >
        </sidebar-item>
          <sidebar-item
          :link="{
            name: $t('Charts Reports'),
            icon: 'tim-icons icon-simple-add',
            path: '/chartsReports'
          }"
        >
         </sidebar-item>
        </sidebar-item>
          <sidebar-item
          :link="{
            name: $t('Users'),
            icon: 'tim-icons icon-badge',
            path: '/users'
          }"
        >
        </sidebar-item>
          <sidebar-item
          :link="{
            name: $t('Enterprise'),
            icon: 'tim-icons icon-single-02',
            path: '/enterprise'
          }"
        >
        
        </sidebar-item>
        <sidebar-item
          :link="{
            name: $t('Configuration'),
            icon: 'tim-icons icon-settings',
            path: '/configuration'
          }"
        >
        </sidebar-item>
       
       
      </template>
    </side-bar>
    <div class="main-panel" :data="sidebarBackground">
      <dashboard-navbar></dashboard-navbar>
      <router-view name="header"></router-view>

      <div
        :class="{ content: !$route.meta.hideContent }"
        @click="toggleSidebar"
      >
        <zoom-center-transition :duration="200" mode="out-in">
          <!-- your content here -->
          <router-view></router-view>
        </zoom-center-transition>
      </div>
    </div>
  </div>
</template>
<script>
/* eslint-disable no-new */
import PerfectScrollbar from 'perfect-scrollbar';
import 'perfect-scrollbar/css/perfect-scrollbar.css';

function hasElement(className) {
  return document.getElementsByClassName(className).length > 0;
}

function initScrollbar(className) {
  if (hasElement(className)) {
    new PerfectScrollbar(`.${className}`);
  } else {
    // try to init it later in case this component is loaded async
    setTimeout(() => {
      initScrollbar(className);
    }, 100);
  }
}

import DashboardNavbar from './SampleNavbar.vue';
import DashboardContent from '../Layout/Content.vue';
import SidebarFixedToggleButton from '../Layout/SidebarFixedToggleButton.vue';
import { SlideYDownTransition, ZoomCenterTransition } from 'vue2-transitions';

export default {
  components: {
    DashboardNavbar,
    SidebarFixedToggleButton,
    DashboardContent,
    SlideYDownTransition,
    ZoomCenterTransition
  },
  data() {
    return {
      sidebarBackground: 'red' //vue|blue|orange|green|red|primary
    };
  },
  methods: {
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    },
    initScrollbar() {
      let docClasses = document.body.classList;
      let isWindows = navigator.platform.startsWith('Win');
      if (isWindows) {
        // if we are on windows OS we activate the perfectScrollbar function
        initScrollbar('sidebar');
        initScrollbar('main-panel');
        initScrollbar('sidebar-wrapper');

        docClasses.add('perfect-scrollbar-on');
      } else {
        docClasses.add('perfect-scrollbar-off');
      }
    }
  },
  mounted() {
    this.initScrollbar();
  }
};
</script>
<style lang="scss">
$scaleSize: 0.95;
@keyframes zoomIn95 {
  from {
    opacity: 0;
    transform: scale3d($scaleSize, $scaleSize, $scaleSize);
  }
  to {
    opacity: 1;
  }
}

.main-panel .zoomIn {
  animation-name: zoomIn95;
}

@keyframes zoomOut95 {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
    transform: scale3d($scaleSize, $scaleSize, $scaleSize);
  }
}

.main-panel .zoomOut {
  animation-name: zoomOut95;
}
</style>
