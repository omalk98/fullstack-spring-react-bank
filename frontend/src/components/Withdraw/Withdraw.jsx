import { useState, useEffect } from 'react';
import {
  Row,
  Col,
  Form,
  InputGroup,
  Button,
  Container,
  Card,
  DropdownButton,
  Dropdown,
  Alert,
} from 'react-bootstrap';

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

//validate if the input is a number
//validate if customer has enough money to withdraw
//validate if the amount is not greater than the balance
//validate if the amount is not greater than the max withdraw amount
export default function Withdraw() {
  const [amount, setAmount] = useState(0.0);

  //the below variable could be an object of acct number and balance
  const [accountNumbers, setAccountNumbers] = useState([123, 456, 789]);

  const [error, setError] = useState('');
  const [variant, setVariant] = useState('danger');
  const [selectedAccountNo, setSelectedAccountNo] = useState(123);

  const handleSubmit = (event) => {
    event.preventDefault();

    if (isNaN(event.target[0].value)) {
      setVariant('danger');
      setError('Please enter a number for withdrawal amount.');
    } else {
      //make an api call to deposit
      //depending on the response, show a message
      //saying that the deposit was successful
      setError('Deposit Successful');
      setVariant('success');
      setAmount(event.target[0].value);
    }
  };

  return (
    <Container fluid style={{ ...styleForHorizontalCenter }}>
      <Card style={{ ...paperStyle }}>
        <Form onSubmit={handleSubmit}>
          <Form.Group md='3'>
            <Form.Label style={{ fontSize: '25px', color: 'black' }}>
              Withdrawal Amount
            </Form.Label>
            <br />
            <br />
            <InputGroup>
              <Row style={{ paddingLeft: '15%' }}>
                <Col>
                  <Form.Control
                    type='text'
                    placeholder='0.00'
                    aria-describedby='inputGroupPrepend'
                    required
                    value={amount}
                    onChange={(e) => {
                      setAmount(e.target.value);
                      setError('');
                    }}
                  />
                  <br />
                </Col>
                <Col>
                  <DropdownButton
                    key='primary'
                    id={`dropdown-variants-secondary`}
                    variant='success'
                    title='Account number'
                    style={{ textAlign: 'right' }}
                    onSelect={(e) => {
                      setSelectedAccountNo(e);
                    }}
                  >
                    {accountNumbers.map((accountNumber) => (
                      <Dropdown.Item
                        eventKey={accountNumber}
                        key={accountNumber}
                      >
                        {accountNumber}
                      </Dropdown.Item>
                    ))}
                  </DropdownButton>
                </Col>
              </Row>
            </InputGroup>
          </Form.Group>
          {error && (
            <Alert
              style={{ width: '24rem' }}
              className='m-auto'
              variant={variant}
            >
              {error}
            </Alert>
          )}
          <br />
          <Button variant='primary' type='submit' size='lg'>
            Withdraw
          </Button>{' '}
          &nbsp; &nbsp; &nbsp;
          <Button href={`/customer`} variant='success' size='lg'>
            Go Back
          </Button>
        </Form>
      </Card>
    </Container>
  );
}
