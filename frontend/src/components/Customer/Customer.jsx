import React, { useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import { Container, Row, Col, Button, Card } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

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

//need to add a view/button that allows a user to
//open a new account
export default function Customer(props) {
  const navigate = useNavigate();
  let user = JSON.parse(localStorage.getItem('user'));

  const handleSubmit = (e) => {
    e.preventDefault();
    localStorage.setItem('isAuthenticated', false);
    navigate('/');
  };

  return (
    <Container fluid style={{ ...styleForHorizontalCenter }}>
      <Row>
        <Col>
          <Card style={paperStyle}>
            <div style={{ color: 'black' }}>
              <h1>
                Hello {props.user?.firstName} {props.user?.lastName}!
              </h1>
              <br />
              <h2>Welcome to our banking app!</h2>
              <br />
              <h3>What has brought you here!</h3>
              <br />
            </div>
            <Button
              variant='primary'
              size='lg'
              style={{ width: '10rem', margin: '0 auto' }}
              onClick={() => {
                navigate('/customer/deposit');
              }}
            >
              Deposit
            </Button>{' '}
            <br />
            <Button
              variant='primary'
              size='lg'
              style={{ width: '10rem', margin: '0 auto' }}
              onClick={() => {
                navigate('/customer/withdraw');
              }}
            >
              Withdraw
            </Button>{' '}
            <br />
            <Button
              variant='primary'
              size='lg'
              style={{ width: '10rem', margin: '0 auto' }}
              onClick={() => {
                navigate('/customer/transfer');
              }}
            >
              Transfer
            </Button>{' '}
            <br />
            <Button
              variant='primary'
              size='lg'
              style={{ width: '11rem', margin: '0 auto' }}
              onClick={() => {
                navigate('/customer/balance');
              }}
            >
              Check Balance
            </Button>
            <br />
            <Button
              onClick={(e) => handleSubmit(e)}
              variant='success'
              size='lg'
              style={{ width: '15rem', margin: '0 auto' }}
            >
              Logout
            </Button>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}
