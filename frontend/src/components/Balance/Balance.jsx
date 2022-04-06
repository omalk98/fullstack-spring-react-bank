import axios from 'axios';
import { useState, useEffect } from 'react';
import { Row, Col, Button, Card, Container } from 'react-bootstrap';

//fetch the balance from the server and display it
//a customer can have more than one account
export default function Balance() {
  const [balance, setBalance] = useState([]);

  const styleForHorizontalCenter = {
    position: 'relative',
    top: '50%',
    transform: 'translateY(10%)',
    textAlign: 'center',
    width: '18rem',
  };

  //api should respond with an array of bank account numbers +
  //balance for the each of the bank account number
  useEffect(() => {
    axios
      .get('http://localhost:8080/api')
      .then((res) => setBalance(res.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <Container fuild style={{ ...styleForHorizontalCenter }}>
      <Row>
        <Col>
          {/* loop through the array of bank account numbers and balance and show each card*/}
          <Card>
            <Card.Body>
              <Card.Title style={{ color: 'black' }}>
                Show Bank Account Number <strong>123</strong> Here
              </Card.Title>
              <Card.Text style={{ color: 'black' }}>
                Show balance of the user <strong>$50,000</strong> here
              </Card.Text>
            </Card.Body>
          </Card>
          <br />
          <Card>
            <Card.Body>
              <Card.Title style={{ color: 'black' }}>
                Show Bank Account Number <strong>123</strong> Here
              </Card.Title>
              <Card.Text style={{ color: 'black' }}>
                Show balance of the user <strong>$50,000</strong> here
              </Card.Text>
            </Card.Body>
          </Card>
          <br />
          <Button href={`/customer`} variant='success' size='lg'>
            Go Back
          </Button>
        </Col>
      </Row>
    </Container>
  );
}
