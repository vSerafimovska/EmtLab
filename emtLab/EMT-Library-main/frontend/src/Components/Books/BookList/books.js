import React, {Component} from "react";
import BookTerm from '../BookTerm/bookTerm'
import ReactPaginate from "react-paginate";
import {Link} from "react-router-dom";


class Books extends Component {

    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 5
        }
    }

    render() {

        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length / this.state.size);
        const books = this.getBooksPage(offset, nextPageOffset)

        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Category</th>
                                <th scope={"col"}>Author</th>
                                <th scope={"col"}>AvailableCopies</th>
                            </tr>
                            </thead>
                            <tbody>
                            {books}
                            </tbody>
                        </table>
                        <ReactPaginate previousLabel={"Previous"}
                                       previousLinkClassName={"page-link"}
                                       nextLabel={"Next"}
                                       nextLinkClassName={"page-link"}
                                       breakLabel={<a href="/#">...</a>}
                                       breakClassName={"page-item"}
                                       pageClassName={"mx-1 page-item"}
                                       pageLinkClassName={"page-link"}
                                       pageCount={pageCount}
                                       marginPagesDisplayed={2}
                                       pageRangeDisplayed={5}
                                       onPageChange={this.handlePageClick}
                                       containerClassName={"pagination m-4 justify-content-center"}
                                       activeClassName={"active"}/>
                        <div className="col mb-3">
                            <div className="row">
                                <div className="col-sm-12 col-md-12">
                                    <Link className={"btn btn-block btn-dark"} to={"/books/add"}>Add a new book</Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }
    getBooksPage = (offset, nextPageOffset) => {
        return this.props.books.map((term) => {
            return (
                <BookTerm term={term}
                          onDelete={this.props.onDelete}
                          onEdit={this.props.onEdit}
                          onMarkAsTaken={this.props.onMarkAsTaken}/>
            );
        }).filter((book, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }
}
export default Books;