import React from "react";
import {useNavigate} from "react-router-dom";

const BookEdit = (props) => {

    const history = useNavigate();

    const [formData, updateFormData] = React.useState({
        name: "",
        category: "THRILLER",
        author: 0,
        availableCopies: 0,
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.book.name;
        const category = formData.category !== "THRILLER" ? formData.category : props.book.category;
        const author = formData.author !== 0 ? formData.author : props.book.author.id;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;

        props.onEditBook(props.book.id, name, category, author, availableCopies);
        history("/books");
    }


    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder={props.book.availableCopies}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((element) =>{
                                    if(props.book.category !== undefined &&
                                    props.book.category === element)
                                        return (<option selected={element}
                                                       value={props.book.category}>{element}</option>);
                                    else
                                        return (<option value={element}>{element}</option>);
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((element) =>{
                                if(props.book.author !== undefined &&
                                    props.book.author.id === element.id)
                                    return (<option selected={props.book.author.id}
                                                   value={element.id}>{element.name + ' ' + element.surname}</option>);
                                else
                                    return (<option value={element.id}>{element.name + ' ' + element.surname}</option>);
                            })}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary mt-2">Submit</button>
                </form>
            </div>
        </div>
    );
}

export default BookEdit;