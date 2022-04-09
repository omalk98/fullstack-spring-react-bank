import React, { useState, useEffect } from 'react';
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
};

//validate the input
//validate if the 'from' bank account has enough money to transfer else show error
export default function Transfer(props) {
  const [amount, setAmount] = useState(0.0);

  //the below variable could be an object of acct number and balance
  const [accountNumbers, setAccountNumbers] = useState([
    { acctNo: 123, balance: 50_000 },
    { acctNo: 456, balance: 40_000 },
    { acctNo: 789, balance: 30_000 },
  ]);
  const [error, setError] = useState('');
  const [variant, setVariant] = useState('danger');
  const [from, setFrom] = useState(123);
  const [to, setTo] = useState(456);

  const handleSubmit = (event) => {
    event.preventDefault();

    if (isNaN(event.target[0].value)) {
      setVariant('danger');
      setError('Please enter a number for transfer amount.');
    } else {
      if (from === to) {
        setVariant('danger');
        setError('Please select different account numbers.');
      } else {
        const fromBalance = accountNumbers.find(
          (account) => account.acctNo === from
        )?.balance;

        if (fromBalance < amount) {
          setVariant('danger');
          setError('Insufficient funds.');
        } else {
          // axios
          //   .put(
          //     `http://localhost:8080/bankAccount/transfer?from=${from}&to=${to}&amount=${amount}`
          //   )
          //   .then((response) => {
          //     if (response) {
          //       setVariant('success');
          //       setError('Transfer Successful');
          //     } else {
          //       setVariant('danger');
          //       setError('Transfer Failed');
          //     }
          //   })
          //   .catch((err) => {
          //     setError('Transfer Failed');
          //     setVariant('danger');
          //   });
        }
      }
    }
  };

  //the moment the component gets mounted
  //api should be called which respond with an array of bank account numbers +
  //balance for the each of the bank account number
  useEffect(() => {
    // axios
    //   .get('http://localhost:8080/bankAccount/getAllBankAccount?userId={props.id}')
    //   .then((res) => setAccountNumbers(res))
    //   .catch((err) => console.log(err));
  }, []);

  return (
    <Container fluid style={{ ...styleForHorizontalCenter }}>
      <Card style={{ ...paperStyle }}>
        <Alert
          variant='success'
          style={{
            height: '3rem',
          }}
          className='m-auto'
        >
          <Alert.Heading style={{ marginTop: '-7px' }}>
            Hello User abc!
          </Alert.Heading>
        </Alert>
        <br></br>
        <Row className='m-auto'>
          <Col style={{}}>
            <DropdownButton
              key='primary'
              id={`dropdown-variants-secondary`}
              variant='success'
              title='From'
              size='lg'
              style={{ textAlign: 'right' }}
            >
              {accountNumbers.map((accountNumber, index) => (
                <Dropdown.Item
                  eventKey={index}
                  key={index}
                  active={from === accountNumber.acctNo}
                  onClick={(e) => {
                    setFrom(parseInt(e.target.innerText));
                    setError('');
                  }}
                >
                  {accountNumber.acctNo}
                </Dropdown.Item>
              ))}
            </DropdownButton>
          </Col>
          <Col>
            <DropdownButton
              key='primary'
              id={`dropdown-variants-secondary`}
              variant='success'
              title='To'
              size='lg'
              style={{ textAlign: 'right' }}
            >
              {accountNumbers.map((accountNumber, index) => (
                <Dropdown.Item
                  eventKey={index}
                  key={index}
                  active={to === accountNumber.acctNo}
                  onClick={(e) => {
                    setTo(parseInt(e.target.innerText));
                    setError('');
                  }}
                >
                  {accountNumber.acctNo}
                </Dropdown.Item>
              ))}
            </DropdownButton>
          </Col>
        </Row>
        <br></br>
        <Form onSubmit={handleSubmit} className='m-auto'>
          <Row>
            <Form.Group md='3' style={{ textAlign: 'center' }}>
              <Form.Label style={{ fontSize: '25px', color: 'black' }}>
                Transfer Amount
              </Form.Label>
              <InputGroup hasValidation>
                <div style={{ width: '20rem' }}>
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
                </div>
              </InputGroup>
            </Form.Group>
          </Row>
          &nbsp;
          {error && (
            <Alert
              style={{ width: '20rem', textAlign: 'center' }}
              className='m-auto'
              variant={variant}
            >
              {error}
            </Alert>
          )}
          <br />
          <Row>
            <Button
              variant='primary'
              type='submit'
              size='lg'
              style={{ width: '10rem' }}
            >
              Transfer
            </Button>{' '}
            &nbsp; &nbsp; &nbsp;
            <Button
              href={`/customer`}
              variant='success'
              size='lg'
              style={{ width: '10rem' }}
            >
              Go Back
            </Button>
          </Row>
        </Form>
      </Card>
    </Container>
  );
}
