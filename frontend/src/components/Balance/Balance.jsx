import axios from 'axios';
import { useState, useEffect } from 'react';
import { Row, Col, Button, Card, Container } from 'react-bootstrap';
import NavbarCollapse from 'react-bootstrap/esm/NavbarCollapse';

//fetch the balance from the server and display it
//a customer can have more than one account

//calls a route in the backend server that requires currently
//logged in user's id, so Balance needs a currently logged in user's id
//to be passed in as props
export default function Balance(props) {
  const [customerData, setCustomerData] = useState([
    { acctNo: 123, balance: 50_000 },
    { acctNo: 456, balance: 40_000 },
    { acctNo: 789, balance: 30_000 },
  ]);

  const styleForHorizontalCenter = {
    position: 'relative',
    top: '50%',
    transform: 'translateY(10%)',
    textAlign: 'center',
    width: '18rem',
  };

  //the moment the component gets mounted
  //api should respond with an array of bank account numbers +
  //balance for the each of the bank account number
  useEffect(() => {
    // axios
    //   .get('http://localhost:8080/bankAccount/getAllBankAccount?userId={props.id}')
    //   .then((res) => setCustomerData(res))
    //   .catch((err) => console.log(err));
  }, []);

  return (
    <Container fuild='true' style={{ ...styleForHorizontalCenter }}>
      <Row>
        <Col>
          {/* loop through the array of bank account numbers and balance and show each card*/}
          {customerData.map((cData, index) => (
            <Card key={index}>
              <Card.Body>
                <Card.Title style={{ color: 'black' }}>
                  Account Number: <strong>{cData.acctNo}</strong>
                </Card.Title>
                <Card.Text style={{ color: 'black' }}>
                  Balance: <strong>${cData.balance}</strong>
                </Card.Text>
              </Card.Body>
            </Card>
          ))}
          <br />
          <Button href={`/customer`} variant='success' size='lg'>
            Go Back
          </Button>
        </Col>
      </Row>
    </Container>
  );
}
