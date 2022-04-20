import React, {Component} from 'react';
import {Container, Table} from "react-bootstrap";

class TaskList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tasks: [
                {
                    id: 1,
                    name: "Jolles",
                    category: "Education",
                    description: "Do your homework",
                    date: "4/20"
                }
            ]
        }
    }

    componentDidMount() {
    }


    render() {
        return (
            <Container className={"my-3"}>
                <Table className={"striped table-bordered table-hover"}>
                    <thead>
                        <tr>
                            <th>Creator</th>
                            <th>Category</th>
                            <th>Task</th>
                            <th>Date Created</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.tasks.map(
                            task =>
                                <tr key={task.id}>
                                    <td>{task.name}</td>
                                    <td>{task.category}</td>
                                    <td>{task.description}</td>
                                    <td>{task.date}</td>
                                </tr>
                        )
                    }
                    </tbody>
                </Table>
            </Container>
        );
    }
}

export default TaskList;