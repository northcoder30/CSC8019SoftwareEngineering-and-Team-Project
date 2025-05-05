import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/books'; // Adjust the URL to match your Spring Boot backend

axios.get("http://localhost:8080/api/books")
    .then(response => console.log(response.data));

export default {
    getAllBooks() {
        return axios.get(API_BASE_URL);
    },
    getBookById(id) {
        return axios.get(`${API_BASE_URL}/${id}`);
    }

};