const PROXY_URL = 'https://cors-anywhere.herokuapp.com/'; 
const url = 'http://pi2sis.icesi.edu.co/medvedapi' 
// const url = 'http://localhost:8006/medvedapi'

import axios from "axios"

function getInternalTicket(id){
    return fetch(`${url}/internalTickets/${id}`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function getCountUsers(){
    return fetch(`${url}/users/count`)
    .then(ans => ans.json())
}

function getExternalTicket(id){
    return fetch(`${url}/externalTickets/${id}`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function getAllExternalTicket(perPage,currentPage){
    return fetch(`${url}/externalTickets/${perPage}/${currentPage}`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json()); 
}
function getAllInternalTicketAssigned(perPage,currentPage){
    return fetch(`${url}/internalTickets/assigned/${perPage}/${currentPage}/`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function getAllInternalTicketResolved(perPage,currentPage){
    return fetch(`${url}/internalTickets/resolved/${perPage}/${currentPage}/`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function findByKeyWordInternalResolved(keyword,perPage,currentPage){
    return fetch(`${url}/internalTickets/resolved/meta/${keyword}/${perPage}/${currentPage}/`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function findByKeyWordInternalAssigned(keyword,perPage,currentPage){
    return fetch(`${url}/internalTickets/assigned/meta/${keyword}/${perPage}/${currentPage}/`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function findByKeyWordExternal(keyword,perPage,currentPage){
    return fetch(`${url}/externalTickets/meta/${keyword}/${perPage}/${currentPage}`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function getAllEnterprises(){
    return fetch(`${url}/enterprises`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}
function getAllSynchReports(){
    return fetch(`${url}/synchronizationRegister`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
    
    
}
function getCountSynchReports(){
    return fetch(`${url}/statistics/syncRegisters/number`,
    {
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
    
    
}

function getInternalPerEnterprise(){
    return fetch(`${url}/statistics/internalTickets/enterprises`,
    {
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
    
    
}
function getCountInternalTickets(){
    return fetch(`${url}/statistics/internalTickets/number`,
    {
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
    
    
}
function getCountExternalTickets(){
    return fetch(`${url}/statistics/externalTickets/number`,
    {
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
    
    
}
function getCountEnterprises(){
    return fetch(`${url}/statistics/enterprises/number`,
    {
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
    
    
}
function getAllPriorities(){
    return fetch(`${url}/priorities`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function saveNewTicket(body){
    return fetch(`${url}/internalTickets/`,
    {
        method: 'POST',
        body: body,
        headers: {
            'Content-Type': 'application/json',
            'Authorization':localStorage.getItem("Token"),
        }
    }
    ).then(res => res.json())
    .catch(error => console.error('Error:', error))
    .then(response => console.log('Success:', response));


}


function initialLoadDB(sourceId,firstDate,lastDate){
    
    return fetch(`${url}/externalTickets/source/${sourceId}/dates/${firstDate}/${lastDate}`,
    {
        method: 'GET',
        headers:{
                'Authorization':localStorage.getItem("Token"),
        }
    }
    )

}

function getCreatedInternalPerMonth(start,end){
    return fetch(`${url}/statistics/internalTickets/created/${start}/${end}`,
    {
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
    
    
}

function getClosedInternalPerMonth(start,end){
    return fetch(`${url}/statistics/internalTickets/closed/${start}/${end}`,
    {
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
    
    
}

function loadDB(sourceId,lastDate,synchronizationHour,repeat){
    
    return fetch(`${url}/externalTickets/source/${sourceId}/dates/${lastDate}/hour/${synchronizationHour}/repeats/${repeat}`,
    {
        method: 'GET',
        headers:{
                'Authorization':localStorage.getItem("Token"),
        }
    }
    )
}

function editTicket(body){
   

    return fetch(`${url}/internalTickets/`,
    {
        method: 'PUT',
        body: body,
        headers: {
            'Content-Type': 'application/json',
            'Authorization':localStorage.getItem("Token")
        }
    }
    ).then(res => res.json())
    .catch(error => console.error('Error:', error))
    .then(response => console.log('Success:', response));
}

function deleteTicket(id){
    
    return fetch(`${url}/internalTickets/${id}`,
    {
        method: 'DELETE',
        headers:{
                'Authorization':localStorage.getItem("Token"),
            }
        
    }
    )
}

function deleteUser(id){
    
    return fetch(`${url}/users/${id}`,
    {
        method: 'DELETE',
        headers:{
                'Authorization':localStorage.getItem("Token"),
            }
        
    }
    )
}
function deleteEnterprise(id){
    
    return fetch(`${url}/enterprises/${id}`,
    {
        method: 'DELETE',
        headers:{
                'Authorization':localStorage.getItem("Token"),
            }
        
    }
    )
}

async function answerTicket(body){
    return fetch(`${url}/internalTickets/answer`,
    {
        method: 'POST',
        body: body,
        headers: {
            'Content-Type': 'application/json',
            'Authorization':localStorage.getItem("Token"),
        }
    }
    ).then(res => res.json());

}
async function saveAnswerImage(body,form){

    const idAnswer = await answerTicket(body);
    
    if(form.file!=null){
        axios.post(`${url}/internalTickets/image/${idAnswer}`,form);
        alert("entro!!!!!!!!!!!!!!!!")
    }
    
}

function saveNewEnterprise(body){


    return fetch(`${url}/enterprises`,
    {
        method: 'POST',
        body: body,
        headers: {
            'Content-Type': 'application/json',
            'Authorization':localStorage.getItem("Token")
        }
    }
    ).then(res => res.json())
    .catch(error => console.error('Error:', error))
    .then(response => console.log('Success:', response));


}
function saveNewUser(body){
    return fetch(`${url}/users`,
    {
        method: 'POST',
        body: body,
        headers: {
            'Content-Type': 'application/json',
            'Authorization':localStorage.getItem("Token")
        }
    }
    ).then(res => res.json())
    .catch(error => console.error('Error:', error))
    .then(response => console.log('Success:', response));


}
function editEnterprise(body){
    return fetch(`${url}/enterprises`,
    {
        method: 'PUT',
        body: body,
        headers: {
            'Content-Type': 'application/json',
            'Authorization':localStorage.getItem("Token")
        }
    }
    ).then(res => res.json())
    .catch(error => console.error('Error:', error))
    .then(response => console.log('Success:', response));


}
function getEnterprise(id){
    return fetch(`${url}/enterprises/${id}`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}
function getAllSources(){
    
    return fetch(`${url}/sources/`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}
function getAllUsers(){
    
    return fetch(`${url}/users/`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}
function getAllUsersByEnterprise(id){
    console.log(id);
    return fetch(`${url}/users/enterprise/${id}`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function getAgentsbyCompany(){
    
    return fetch(`${url}/users/agents`,{
        method: 'GET',
        headers:{
            'Authorization': localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function getAllRol(){
    
    return fetch(`${url}/rol/`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}
function getTopKeywords(month){
    
    return fetch(`${url}/keywords/topKeywords/${month}`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function getUser(id){
    return fetch(`${url}/users/${id}`,{
        method: 'GET',
        headers:{
            'Authorization':localStorage.getItem("Token"),
        }
    })
    .then(ans => ans.json());
}

function editUser(body){
    return fetch(`${url}/users`,
    {
        method: 'PUT',
        body: body,
        headers: {
            'Content-Type': 'application/json',
            'Authorization':localStorage.getItem("Token")
        }
    }
    ).then(res => res.json())
    .catch(error => console.error('Error:', error))
    .then(response => console.log('Success:', response));


}

function mostCrackValidationMethodInExistanceUpToDate(body){
    return fetch(`${url}/users/authenticate`,
    {
        method: 'POST',
        body: body,
        headers: {
            'Content-Type': 'application/json',
        }
    }
    ).then(res => res.text())
    .catch(error => console.error('Error:', error))
    .then(response => "Bearer " + response);


}
export default{
    getInternalTicket,
    getExternalTicket,
    getAllExternalTicket,
    getAllInternalTicketAssigned,
    getAllInternalTicketResolved,
    saveNewTicket,
    getAllEnterprises,
    getAllSynchReports,
    getAllPriorities,
    editTicket,
    deleteTicket,
    deleteUser,
    deleteEnterprise,
    initialLoadDB,
    loadDB,
    findByKeyWordInternalResolved,
    findByKeyWordInternalAssigned,
    findByKeyWordExternal,
    answerTicket,
    saveAnswerImage,
    url,
    editEnterprise,
    getEnterprise,
    getAllSources,
    getAllUsers,
    getAllUsersByEnterprise,
    getAgentsbyCompany,
    saveNewEnterprise,
    getTopKeywords,
    getCountUsers,
    getCountSynchReports,
    saveNewUser,
    getAllRol,
    getUser,
    editUser,
    mostCrackValidationMethodInExistanceUpToDate,
    getCountInternalTickets,
    getCountExternalTickets,
    getCountEnterprises,
    getInternalPerEnterprise,
    getCreatedInternalPerMonth,
    getClosedInternalPerMonth,
  

}


