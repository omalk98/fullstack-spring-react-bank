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

//calls a route in the backend server that requires currently
//logged in user's id, so Balance needs a currently logged in user's id
//to be passed in as props
export default function Withdraw(props) {
  const [amount, setAmount] = useState(0.0);

  const [accountNumbers, setAccountNumbers] = useState([
    { acctNo: 123, balance: 50_000 },
    { acctNo: 456, balance: 40_000 },
    { acctNo: 789, balance: 30_000 },
  ]);

  const [error, setError] = useState('');
  const [variant, setVariant] = useState('danger');
  const [selectedAccountNo, setSelectedAccountNo] = useState(123);

  const handleSubmit = (event) => {
    event.preventDefault();

    if (isNaN(event.target[0].value)) {
      setVariant('danger');
      setError('Please enter a number for withdrawal amount.');
    } else {
      const balance = accountNumbers.find(
        (account) => account.acctNo === selectedAccountNo
      )?.balance;

      if (balance < event.target[0].value) {
        setVariant('danger');
        setError('Insufficient funds.');
      } else {
        setVariant('success');
        setError('Withdrawal Successful');

        // axios
        //   .put('http://localhost:8080/bankAccount/withdraw?acctNum={selectedAccountNo}&amount={amount}')
        //   .then((res) => {
        //setError('Withdrawal Successful');
        //setVariant('success');
        //setAmount(event.target[0].value);
        //})
        //   .catch((err) => {
        //setError('Withdrawal UnSuccessful');
        //setVariant('danger');
        //});
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
                  >
                    {accountNumbers.map((accountNumber, index) => (
                      <Dropdown.Item
                        eventKey={accountNumber}
                        key={index}
                        active={selectedAccountNo === accountNumber.acctNo}
                        onClick={(e) => {
                          setSelectedAccountNo(parseInt(e.target.innerText));
                        }}
                      >
                        {accountNumber.acctNo}
                      </Dropdown.Item>
                    ))}
                  </DropdownButton>
                </Col>
              </Row>
            </InputGroup>
          </Form.Group>
          {error && (
            <Alert
              style={{ width: '24rem', textAlign: 'center' }}
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
