import React, {Component} from 'react';
import {Container, Navbar} from "react-bootstrap";

class NavigationBar extends Component {
    render() {
        return (
            <Navbar bg={"light"} expand={"lg"}>
                <Container>
                    <Navbar.Brand>Task Manager</Navbar.Brand>
                </Container>
            </Navbar>
        );
    }
}

export default NavigationBar;