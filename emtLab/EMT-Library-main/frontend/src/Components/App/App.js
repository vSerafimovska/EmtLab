import './App.css';
import Books from '../Books/BookList/books'
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";
import Categories from '../Categories/categories'
import {BrowserRouter as Router, Route, Routes, Navigate} from 'react-router-dom'
import Header from '../Header/header';
import {Component} from "react";
import ELibraryService from "../../repository/eLibraryRepository";

class App extends Component{

  constructor(props) {
    super(props);
    this.state = {
      books: [],
      authors: [],
      categoryTypes: [],
      selectedBook: {}
    }
  }

  render() {
    return(
        <Router>
            <Header/>
            <main>
                <div className="container">
                    <Routes>
                     <Route path={"/categories"}
                        element={<Categories categories={this.state.categoryTypes}/>} exact/>
                        <Route path={"/books/edit/:id"}
                               element={<BookEdit categories={this.state.categoryTypes}
                                                  authors={this.state.authors}
                                                  onEditBook={this.editBook}
                                                  book={this.state.selectedBook}
                               />} exact/>
                        <Route path={"/books/add"}
                               element={<BookAdd categories={this.state.categoryTypes}
                                                 authors={this.state.authors}
                                                 onAddBook={this.addBook}/>} exact/>
                        <Route path={"/books"}
                               element={<Books books={this.state.books} onDelete={this.deleteBook}
                                               onEdit={this.fetchBookById}
                                               onMarkAsTaken={this.markBookAsTaken}/>} exact/>
                        {/*<Route path={"/categories"} exact render={() =>*/}
                        {/*    <Categories categoryTypes={this.state.categoryTypes} />} />*/}
                     <Route path="/" element={<Navigate replace to="/books"/>}/>
                    </Routes>
                </div>
            </main>
        </Router>
    )
  }

  loadBooks = () => {
    ELibraryService.fetchBooks()
        .then((data) => {
          this.setState({
            books: data.data
          })
        })
  }
    loadCategories = () => {
        ELibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categoryTypes: data.data
                })
            })
    }

    loadAuthors = () => {
        ELibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            })
    }

    deleteBook = (id) => {
        ELibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            })
    }
    addBook = (name, category, authorId, availableCopies) => {
        ELibraryService.addBook(name, category, authorId, availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }
    editBook = (id, name, category, authorId, availableCopies) => {
        ELibraryService.editBook(id, name, category, authorId, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }
    fetchBookById = (bookId) => {
        ELibraryService.fetchBookById(bookId)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }
    markBookAsTaken = (id, numReserved) => {
        ELibraryService.markBookAsTaken(id, numReserved)
            .then(() => {
                this.loadBooks();
            });
    }

  componentDidMount() {
    this.loadBooks();
    this.loadCategories();
    this.loadAuthors();
  }
}

export default App;
