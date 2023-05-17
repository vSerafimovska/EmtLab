import React from "react";
import {Link, useNavigate} from "react-router-dom";

const BookTerm = (props) => {

    return (
        <tr>
            <td scope={"col"}>{props.term.name}</td>
            <td scope={"col"}>{props.term.bookCategory}</td>
            <td scope={"col"}>{props.term.author.name} {props.term.author.surname}</td>
            <td scope={"col"}>{props.term.availableCopies}</td>
            <td className={"text-right"}>
                <Link className={"btn btn-info mx-1"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>
                <Link className={"btn btn-success"}
                      onClick={() => props.onMarkAsTaken(props.term.id, 1)}>
                    Mark As Taken
                </Link>
                <a  title={"Delete"}
                    className={"btn btn-danger mx-1"}
                    onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
            </td>
        </tr>
    );
}

export default BookTerm;