import axios from 'axios';
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
import { useNavigate } from 'react-router-dom';

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

//calls a route in the backend server that requires currently
//logged in user's id, so Balance needs a currently logged in user's id
//to be passed in as props
export default function Deposit(props) {
  const navigate = useNavigate();

  const [amount, setAmount] = useState(0.0);
  const [accountNumbers, setAccountNumbers] = useState([]);
  const [selectedAccountNo, setSelectedAccountNo] = useState(null);
  const [error, setError] = useState('');
  const [variant, setVariant] = useState('danger');

  const handleSubmit = (event) => {
    event.preventDefault();

    if (isNaN(event.target[0].value)) {
      setVariant('danger');
      setError('Please enter a number for deposit amount.');
    } else {
      var config = {
        method: 'put',
        url: `http://localhost:8080/api/bankAccount/deposit?acctNum=${selectedAccountNo}&amount=${amount}`,
        headers: {
          Authorization: props.user.access_token,
        },
      };

      axios(config)
        .then(function (response) {
          if (response.data) {
            setVariant('success');
            setError('Deposit successful.');
          } else {
            setVariant('danger');
            setError('Deposit unsuccessful.');
          }
        })
        .catch(function (error) {
          if (selectedAccountNo === null) {
            setVariant('danger');
            setError('Please select an account.');
          } else {
            setError('API error.');
            setVariant('danger');
          }
        });
    }
  };

  //the moment the component gets mounted
  //api should be called which respond with an array of bank account numbers +
  //balance for the each of the bank account number
  useEffect(() => {
    var config = {
      method: 'get',
      url: `http://localhost:8080/api/bankAccount/getAllBankAccount?userId=${props.user.id}`,
      headers: {
        Authorization: props.user.access_token,
      },
      data: '',
    };

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data));
        if (response.data.length === 0) {
          setVariant('danger');
          setError('You do not have any bank accounts.');
        } else {
          setAccountNumbers(response.data);
        }
      })
      .catch(function (error) {
        console.log(error);
        setVariant('danger');
        setError('Error in API Call.');
      });
  }, []);

  return (
    <Container fluid style={{ ...styleForHorizontalCenter }}>
      <Card style={{ ...paperStyle }}>
        <Form onSubmit={handleSubmit}>
          <Form.Group md='3'>
            <Form.Label style={{ fontSize: '25px', color: 'black' }}>
              Deposit Amount
            </Form.Label>
            <br />
            <br />
            <InputGroup>
              <Row style={{ paddingLeft: '12%' }}>
                <Col>
                  <Form.Control
                    type='text'
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
                    {accountNumbers.map(({ accountNumber }, index) => (
                      <Dropdown.Item
                        eventKey={accountNumber}
                        key={index}
                        active={selectedAccountNo === accountNumber}
                        onClick={(e) => {
                          setSelectedAccountNo(parseInt(e.target.innerText));
                        }}
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
              style={{ width: '23rem', textAlign: 'center' }}
              className='m-auto'
              variant={variant}
            >
              {error}
            </Alert>
          )}
          <br />
          <Button variant='primary' type='submit' size='lg'>
            Deposit
          </Button>{' '}
          &nbsp; &nbsp; &nbsp;
          <Button
            variant='success'
            size='lg'
            onClick={() => {
              navigate('/customer');
            }}
          >
            Go Back
          </Button>
        </Form>
      </Card>
    </Container>
  );
}
