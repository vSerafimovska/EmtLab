import axios from "axios";

const instance = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Credentials': 'true',
        'Access-Control-Allow-Methods' : 'GET,PUT,POST,DELETE',
        'Access-Control-Allow-Headers' : 'Content-Type'
        //'Authorization': localStorage.getItem("JWT")
    }
})

export default instance;
