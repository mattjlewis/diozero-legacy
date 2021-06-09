package com.diozero.remote.mqtt;

/*-
 * #%L
 * Organisation: diozero
 * Project:      diozero - Remote Common
 * Filename:     MqttProviderConstants.java
 *
 * This file is part of the diozero project. More information about this project
 * can be found at https://www.diozero.com/.
 * %%
 * Copyright (C) 2016 - 2021 diozero
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

public interface MqttProviderConstants {
	int DEFAULT_QOS = 0;
	boolean DEFAULT_RETAINED = false;

	String ROOT_TOPIC = "diozero";

	String REQUEST_TOPIC = ROOT_TOPIC + "/request";
	String RESPONSE_TOPIC = ROOT_TOPIC + "/response";
	String NOTIFICATION_TOPIC = ROOT_TOPIC + "/notification";
	String GET_BOARD_GPIO_INFO_TOPIC = ROOT_TOPIC + "/boardGpioInfo";

	// GPIO
	String GPIO_SUB_TOPIC = "/gpio";
	String GPIO_REQUEST_TOPIC = REQUEST_TOPIC + GPIO_SUB_TOPIC;
	// GPIO Requests
	String GPIO_PROVISION_DIGITAL_INPUT_TOPIC = GPIO_REQUEST_TOPIC + "/in";
	String GPIO_PROVISION_DIGITAL_OUTPUT_TOPIC = GPIO_REQUEST_TOPIC + "/out";
	String GPIO_PROVISION_DIGITAL_INPUT_OUTPUT_TOPIC = GPIO_REQUEST_TOPIC + "/inout";
	String GPIO_PROVISION_PWM_OUTPUT_TOPIC = GPIO_REQUEST_TOPIC + "/pwm";
	String GPIO_PROVISION_ANALOG_INPUT_TOPIC = GPIO_REQUEST_TOPIC + "/aIn";
	String GPIO_PROVISION_ANALOG_OUTPUT_TOPIC = GPIO_REQUEST_TOPIC + "/aOut";
	String GPIO_DIGITAL_READ_TOPIC = GPIO_REQUEST_TOPIC + "/read";
	String GPIO_DIGITAL_WRITE_TOPIC = GPIO_REQUEST_TOPIC + "/write";
	String GPIO_PWM_READ_TOPIC = GPIO_REQUEST_TOPIC + "/pwmRead";
	String GPIO_PWM_WRITE_TOPIC = GPIO_REQUEST_TOPIC + "/pwmWrite";
	String GPIO_GET_PWM_FREQUENCY_TOPIC = GPIO_REQUEST_TOPIC + "/getPwmFrequency";
	String GPIO_SET_PWM_FREQUENCY_TOPIC = GPIO_REQUEST_TOPIC + "/setPwmFrequency";
	String GPIO_ANALOG_READ_TOPIC = GPIO_REQUEST_TOPIC + "/analogRead";
	String GPIO_ANALOG_WRITE_TOPIC = GPIO_REQUEST_TOPIC + "/analogWrite";
	String GPIO_EVENTS_TOPIC = GPIO_REQUEST_TOPIC + "/events";
	String GPIO_CLOSE_TOPIC = GPIO_REQUEST_TOPIC + "/close";
	// GPIO Responses
	String GPIO_DIGITAL_READ_RESPONSE_TOPIC = RESPONSE_TOPIC + "/gpioDigitalRead";
	String GPIO_PWM_READ_RESPONSE_TOPIC = RESPONSE_TOPIC + "/gpioPwmRead";
	String GPIO_GET_PWM_FREQUENCY_RESPONSE_TOPIC = RESPONSE_TOPIC + "/gpioGetPwmFrequency";
	String GPIO_ANALOG_READ_RESPONSE_TOPIC = RESPONSE_TOPIC + "/gpioAnalogRead";
	String GPIO_NOTIFICATION_TOPIC = NOTIFICATION_TOPIC + GPIO_SUB_TOPIC;

	// I2C
	String I2C_SUB_TOPIC = "/i2c";
	String I2C_REQUEST_TOPIC = REQUEST_TOPIC + I2C_SUB_TOPIC;
	// I2C Requests
	String I2C_OPEN_TOPIC = I2C_REQUEST_TOPIC + "/open";
	String I2C_PROBE_TOPIC = I2C_REQUEST_TOPIC + "/probe";
	String I2C_WRITE_QUICK_TOPIC = I2C_REQUEST_TOPIC + "/writeQuick";
	String I2C_READ_BYTE_TOPIC = I2C_REQUEST_TOPIC + "/readByte";
	String I2C_WRITE_BYTE_TOPIC = I2C_REQUEST_TOPIC + "/writeByte";
	String I2C_READ_BYTES_TOPIC = I2C_REQUEST_TOPIC + "/read";
	String I2C_WRITE_BYTES_TOPIC = I2C_REQUEST_TOPIC + "/write";
	String I2C_READ_BYTE_DATA_TOPIC = I2C_REQUEST_TOPIC + "/readByteData";
	String I2C_WRITE_BYTE_DATA_TOPIC = I2C_REQUEST_TOPIC + "/writeByteData";
	String I2C_READ_WORD_DATA_TOPIC = I2C_REQUEST_TOPIC + "/readWordData";
	String I2C_WRITE_WORD_DATA_TOPIC = I2C_REQUEST_TOPIC + "/writeWordData";
	String I2C_PROCESS_CALL_TOPIC = I2C_REQUEST_TOPIC + "/processCall";
	String I2C_READ_BLOCK_DATA_TOPIC = I2C_REQUEST_TOPIC + "/readBlockData";
	String I2C_WRITE_BLOCK_DATA_TOPIC = I2C_REQUEST_TOPIC + "/writeBlockData";
	String I2C_BLOCK_PROCESS_CALL_TOPIC = I2C_REQUEST_TOPIC + "/blockProcessCall";
	String I2C_READ_I2C_BLOCK_DATA_TOPIC = I2C_REQUEST_TOPIC + "/readI2CBlockData";
	String I2C_WRITE_I2C_BLOCK_DATA_TOPIC = I2C_REQUEST_TOPIC + "/writeI2CBlockData";
	String I2C_CLOSE_TOPIC = I2C_REQUEST_TOPIC + "/close";
	// I2C Responses
	String I2C_BOOLEAN_RESPONSE_TOPIC = RESPONSE_TOPIC + "/i2cBoolean";
	String I2C_BYTE_RESPONSE_TOPIC = RESPONSE_TOPIC + "/i2cByte";
	String I2C_BYTES_RESPONSE_TOPIC = RESPONSE_TOPIC + "/i2cBytes";
	String I2C_WORD_RESPONSE_TOPIC = RESPONSE_TOPIC + "/i2cWord";
	String I2C_READ_BLOCK_DATA_RESPONSE_TOPIC = RESPONSE_TOPIC + "/i2cReadBlockData";

	// SPI
	String SPI_SUB_TOPIC = "/spi";
	String SPI_REQUEST_TOPIC = REQUEST_TOPIC + SPI_SUB_TOPIC;
	// SPI Requests
	String SPI_OPEN_TOPIC = SPI_REQUEST_TOPIC + "/open";
	String SPI_WRITE_TOPIC = SPI_REQUEST_TOPIC + "/write";
	String SPI_WRITE_AND_READ_TOPIC = SPI_REQUEST_TOPIC + "/writeAndRead";
	String SPI_CLOSE_TOPIC = SPI_REQUEST_TOPIC + "/close";
	// SPI Responses
	String SPI_RXDATA_RESPONSE_TOPIC = RESPONSE_TOPIC + "/spiRxData";

	// Serial
	String SERIAL_SUB_TOPIC = "/serial";
	String SERIAL_REQUEST_TOPIC = REQUEST_TOPIC + SERIAL_SUB_TOPIC;
	// Serial Requests
	String SERIAL_OPEN_TOPIC = SERIAL_REQUEST_TOPIC + "/open";
	String SERIAL_READ_TOPIC = SPI_REQUEST_TOPIC + "/read";
	String SERIAL_READ_BYTE_TOPIC = SPI_REQUEST_TOPIC + "/readByte";
	String SERIAL_WRITE_BYTE_TOPIC = SPI_REQUEST_TOPIC + "/writeByte";
	String SERIAL_READ_BYTES_TOPIC = SPI_REQUEST_TOPIC + "/readBytes";
	String SERIAL_WRITE_BYTES_TOPIC = SPI_REQUEST_TOPIC + "/writeBytes";
	String SERIAL_BYTES_AVAILABLE_TOPIC = SPI_REQUEST_TOPIC + "/bytesAvailable";
	String SERIAL_CLOSE_TOPIC = SPI_REQUEST_TOPIC + "/close";
	// Serial Responses
	String SERIAL_READ_RESPONSE_TOPIC = RESPONSE_TOPIC + "/serialReadResponse";
	String SERIAL_READ_BYTE_RESPONSE_TOPIC = RESPONSE_TOPIC + "/serialReadByte";
	String SERIAL_READ_BYTES_RESPONSE_TOPIC = RESPONSE_TOPIC + "/serialReadBytesResponse";
}
