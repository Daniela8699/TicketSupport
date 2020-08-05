<template>
  <div>
    <h3>Answer Ticket</h3>
    <div class="col-md-15">
      <card class="stacked-form" title="Stacked Form">
        <h4 slot="header" class="card-title"></h4>
        <form @submit.prevent>
          <div class="col-md-2">
            <base-input
            type="text"
            label="Ticket ID"
            :disable="true"
            placeholder= ""
            v-model="tick.ticket.id"
            
          >
          </base-input>
          </div>

          <div>            
            <base-input
            type="text"
            label="Description"
            :disable="true"
            placeholder= ""
            v-model="tick.ticket.title"
          >
          </base-input>
          </div>

        </form>
      </card>
    </div>

   

    <div class="col-md-15">
      <card class="stacked-form" title="Stacked Form">
        <h4 slot="header" class="card-title"></h4>
        <form @submit.prevent enctype='multipart/form-data' method="post">
          <div>         
            <base-input label="Add Answer" >
            <textarea
                  class="form-control"
                  placeholder="Answer"
                  v-model="tick.ticket.answer"
                ></textarea>
            </base-input>
          </div>

          

          <div class="col-md-3 col-sm-3">
            <h5>Attach resource</h5> 
            <div class="fileinput text-center">
            <div class="fileinput-new thumbnail">
            <img :src="image"   />
            </div>
            <div>
            <span class="btn btn-primary btn-simple btn-file">
                <span class="fileinput-new">Select resource</span>  
                <input
                accept="image/*"
                @change="handlePreview"
                type="file"
                name="file"
                class="valid"
                aria-invalid="false"
                />
              </span>
            </div>
            </div>
          </div>
          

          <div class="text-center">
          <base-button v-on:click="answerTicket" native-type="submit" type="primary" class="btn-fill">
            Respond
          </base-button>
          
          
          </div>
        </form>
      </card>
    </div>
  </div>
</template>
<script>

import apiMedved from "@/apiMedved.js";
import axios from "axios";
import {
  ImageUpload 
} from 'src/components/index';

export default {
    name: 'image-upload',
    props: {
      type: {
        type: String,
        default: '',
        description: 'Image upload type (""|avatar)'
      },
      src: {
        type: String,
        default: '',
        description: 'Initial image to display'
      },
      selectText: {
        type: String,
        default: 'Select image'
      },
      changeText: {
        type: String,
        default: 'Change'
      },
      removeText: {
        type: String,
        default: 'Remove'
      }
  },
  data() {
    let avatarPlaceholder = 'img/placeholder.jpg';
    let imgPlaceholder = 'img/image_placeholder.jpg'; 
     
    return {
      idAns: null,
      tick: null,
      file: null,
      placeholder: this.type === 'avatar' ? avatarPlaceholder : imgPlaceholder,
      imagePreview: "https://demos.creative-tim.com/vue-black-dashboard-pro/img/image_placeholder.jpg",
    };
  },
  created() {
    apiMedved
      .getInternalTicket(this.$route.params.id)
      .then(tkt => (this.tick = tkt));
  },
  computed: {
    fileExists() {
      return this.imagePreview !== null;
    },
    image() {
      return this.imagePreview || this.src || this.placeholder ;
    }
  },
  methods:{
   answerTicket() {      
      const form = new FormData();
      form.append("file",this.file);

      apiMedved.saveAnswerImage(JSON.stringify(this.tick.ticket),form);
      this.$router.push("/tickets");    
    },
    handlePreview(event) {
      this.file = event.target.files[0];
      this.imagePreview = URL.createObjectURL(this.file);
      this.$emit('change', this.file,);
    },
    removeFile() {
      this.imagePreview = null;
      this.$emit('change', null);
    }
  }
};
</script>
<style></style>