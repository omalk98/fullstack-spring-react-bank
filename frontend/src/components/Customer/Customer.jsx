import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import { Container, Row, Col, Button } from 'react-bootstrap';
import Paper from '@mui/material/Paper';

export default function Customer() {
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

  const [user, setUser] = React.useState('Abc');
  //let {id} = useParams();
  let id = 1;

  return (
    <Container fluid style={{ ...styleForHorizontalCenter }}>
      <Row>
        <Col>
          <Paper elevation={24} style={paperStyle}>
            <h1>Hello {user}!</h1>
            <br />
            <h2>Welcome to our banking app!</h2>
            <br />
            <h3>What has brought you here!</h3>
            <br />
            <Button href={`/customer/deposit`} variant='primary' size='lg'>
              Deposit
            </Button>{' '}
            <Button href={`/customer/withdraw`} variant='primary' size='lg'>
              Withdraw
            </Button>{' '}
            <br />
            <br />
          </Paper>
        </Col>
      </Row>
    </Container>
  );
}
