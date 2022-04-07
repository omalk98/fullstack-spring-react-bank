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
//add a button to go back to customer page

//validate the input
export default function Transfer() {
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

  return (
    <Container fluid style={{ ...styleForHorizontalCenter }}>
      {/* <Col> */}
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
            >
              <Dropdown.Item eventKey='1'>123</Dropdown.Item>
              <Dropdown.Item eventKey='2'>012</Dropdown.Item>
              <Dropdown.Item eventKey='3' /*active*/>345</Dropdown.Item>
              <Dropdown.Item eventKey='4'>453</Dropdown.Item>
            </DropdownButton>
          </Col>
        </Row>
        <Form
          noValidate
          validated={validated}
          onSubmit={handleSubmit}
          className='m-auto'
        >
          <Row>
            <Form.Group
              md='3'
              controlId='validationCustomWithdrawalAmount'
              style={{ textAlign: 'center' }}
            >
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
                  />
                  <Form.Control.Feedback
                    type='invalid'
                    style={{ fontSize: '25px' }}
                  >
                    Please enter a deposit amount.
                  </Form.Control.Feedback>
                </div>
              </InputGroup>
            </Form.Group>
          </Row>
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
      {/* </Col> */}
    </Container>
  );
}
