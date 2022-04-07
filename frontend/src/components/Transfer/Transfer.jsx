import React, { useState } from 'react';
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
  // textAlign: 'center',
};

//validate the input
export default function Transfer() {
  const [validated, setValidated] = useState(false);
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
      setError('Please enter a number for transfer amount.');
    } else {
      //make an api call to deposit
      //depending on the response, show a message
      //saying that the deposit was successful

      console.log(selectedAccountNo);
      setError('Transfer Successful');
      setVariant('success');
      setAmount(event.target[0].value);
    }
  };

  return (
    <Container fluid style={{ ...styleForHorizontalCenter }}>
      <Card style={{ ...paperStyle }}>
        <Row className='m-auto'>
          <Col style={{}}>
            <Alert
              variant='success'
              style={{
                height: '3rem',
              }}
            >
              <Alert.Heading style={{ marginTop: '-7px' }}>
                Hello User abc!
              </Alert.Heading>
            </Alert>
          </Col>
          <Col>
            <DropdownButton
              //as={ButtonGroup}
              key='primary'
              id={`dropdown-variants-secondary`}
              variant='success'
              title='Account number'
              size='lg'
              style={{ textAlign: 'right' }}
              onSelect={(e) => {
                setSelectedAccountNo(e);
              }}
            >
              {accountNumbers.map((accountNumber) => (
                <Dropdown.Item eventKey={accountNumber} key={accountNumber}>
                  {accountNumber}
                </Dropdown.Item>
              ))}
            </DropdownButton>
          </Col>
        </Row>
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
              style={{ width: '20rem' }}
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
