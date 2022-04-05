import React from 'react';
import { Button, Container, Row, Col, Alert } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import Paper from '@mui/material/Paper';

const styleForHorizontalCenter = {
  position: 'absolute',
  top: '50%',
  transform: 'translateY(-50%)',
  textAlign: 'center',
};

const paperStyle = {
  padding: '50px 20px',
  width: 600,
  margin: '20px auto',
  backgroundColor: '#9fa09e',
};

//if user is logged in(check local storage) then take them to
//customer page else take them to login page

export default function NotFound() {
  return (
    <>
      <Container fluid style={{ ...styleForHorizontalCenter }}>
        <Row>
          <Col>
            <Paper elevation={24} style={paperStyle}>
              <Alert variant='danger'>404 - Page not found!</Alert>
              {localStorage.getItem('isAuthenticated') === 'true' ? (
                <Button href='/customer' variant='primary' size='lg'>
                  Go to Customer Page
                </Button>
              ) : (
                <Button href='/' variant='primary' size='lg'>
                  Go to Login Page
                </Button>
              )}
            </Paper>
          </Col>
        </Row>
      </Container>
    </>
  );
}
