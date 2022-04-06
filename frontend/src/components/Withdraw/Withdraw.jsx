import { useState, useEffect } from 'react';
import {
  Row,
  Col,
  Form,
  InputGroup,
  Button,
  Container,
  Card,
} from 'react-bootstrap';

//validate if the input is a number
//validate if customer has enough money to withdraw
//validate if the amount is not greater than the balance
//validate if the amount is not greater than the max withdraw amount
export default function Withdraw() {
  const paperStyle = {
    padding: '50px 20px',
    width: 600,
    margin: '20px auto',
    backgroundColor: '#9fa09e',
  };

  const styleForHorizontalCenter = {
    position: 'absolute',
    top: '50%',
    transform: 'translateY(-50%)',
    textAlign: 'center',
  };

  const [validated, setValidated] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();
    const form = event.currentTarget;
    console.log(event.target[0].value);
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }
    setValidated(true);
  };

  return (
    <Container fluid style={{ ...styleForHorizontalCenter }}>
      <Row>
        <Col>
          <Card style={{ ...paperStyle }}>
            <Form noValidate validated={validated} onSubmit={handleSubmit}>
              <Form.Group md='3' controlId='validationCustomWithdrawalAmount'>
                <Form.Label style={{ fontSize: '25px' }}>
                  Withdrawal Amount
                </Form.Label>
                <InputGroup hasValidation>
                  <div style={{ width: '20rem' }} className='m-auto'>
                    <Form.Control
                      type='text'
                      placeholder='0.00'
                      aria-describedby='inputGroupPrepend'
                      required
                    />
                    <Form.Control.Feedback
                      type='invalid'
                      style={{ fontSize: '25px' }}
                    >
                      Please enter a withdrawal amount.
                    </Form.Control.Feedback>
                  </div>
                </InputGroup>
              </Form.Group>
              <br />
              <Button variant='primary' type='submit' size='lg'>
                Withdraw
              </Button>{' '}
              <Button href={`/customer`} variant='success' size='lg'>
                Go Back
              </Button>
            </Form>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}
