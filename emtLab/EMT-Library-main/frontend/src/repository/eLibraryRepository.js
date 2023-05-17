import axios from "../custom-axios/axios";

const ELibraryService = {
    fetchBooks: () => {
        return axios.get("/books");
    },
    fetchBookById: (id) => {
        return axios.get(`/books/${id}`);
    },
    fetchCategories: () => {
        return axios.get("/books/categories");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    markBookAsTaken: (id,numReserved) => {
        return axios.get(`/books/reserve/${id}`, {
           params:{
               numReserved : numReserved
           }
        });
    },
    addBook: (name, category, author, availableCopies) => {
        return axios.post("/books/add", {
            "name" : name,
            "category" : category,
            "authorId" : author,
            "availableCopies" : availableCopies,
        });
    },
    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "authorId": author,
            "availableCopies": availableCopies
        });
    }
}

export default ELibraryService;
