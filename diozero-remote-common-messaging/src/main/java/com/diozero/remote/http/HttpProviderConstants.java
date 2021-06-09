package com.diozero.remote.http;

/*-
 * #%L
 * Organisation: diozero
 * Project:      diozero - Remote Common
 * Filename:     HttpProviderConstants.java
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

public interface HttpProviderConstants {
	int DEFAULT_QOS = 0;
	boolean DEFAULT_RETAINED = false;

	String ROOT_URL = "/diozero";

	String REQUEST_URL = ROOT_URL + "/request";
	String RESPONSE_URL = ROOT_URL + "/response";
	String NOTIFICATION_URL = ROOT_URL + "/notification";

	String GET_BOARD_GPIO_INFO = ROOT_URL + "/boardGpioInfo";

	// GPIO
	String GPIO_SUB_URL = "/gpio";
	String GPIO_REQUEST_URL = REQUEST_URL + GPIO_SUB_URL;
	// GPIO Requests
	String GPIO_PROVISION_DIGITAL_INPUT_URL = GPIO_REQUEST_URL + "/in";
	String GPIO_PROVISION_DIGITAL_OUTPUT_URL = GPIO_REQUEST_URL + "/out";
	String GPIO_PROVISION_DIGITAL_INPUT_OUTPUT_URL = GPIO_REQUEST_URL + "/inout";
	String GPIO_PROVISION_PWM_OUTPUT_URL = GPIO_REQUEST_URL + "/pwm";
	String GPIO_PROVISION_ANALOG_INPUT_URL = GPIO_REQUEST_URL + "/aIn";
	String GPIO_PROVISION_ANALOG_OUTPUT_URL = GPIO_REQUEST_URL + "/aOut";
	String GPIO_DIGITAL_READ_URL = GPIO_REQUEST_URL + "/digitalRead";
	String GPIO_DIGITAL_WRITE_URL = GPIO_REQUEST_URL + "/digitalWrite";
	String GPIO_PWM_READ_URL = GPIO_REQUEST_URL + "/pwmRead";
	String GPIO_PWM_WRITE_URL = GPIO_REQUEST_URL + "/pwmWrite";
	String GPIO_GET_PWM_FREQUENCY_URL = GPIO_REQUEST_URL + "/getPwmFrequency";
	String GPIO_SET_PWM_FREQUENCY_URL = GPIO_REQUEST_URL + "/setPwmFrequency";
	String GPIO_ANALOG_READ_URL = GPIO_REQUEST_URL + "/analogRead";
	String GPIO_ANALOG_WRITE_URL = GPIO_REQUEST_URL + "/analogWrite";
	String GPIO_EVENTS_URL = GPIO_REQUEST_URL + "/events";
	String GPIO_CLOSE_URL = GPIO_REQUEST_URL + "/close";
	// GPIO Responses
	String GPIO_RESPONSE_URL = RESPONSE_URL + GPIO_SUB_URL;
	String GPIO_NOTIFICATION_URL = NOTIFICATION_URL + GPIO_SUB_URL;

	// I2C
	String I2C_SUB_URL = "/i2c";
	String I2C_REQUEST_URL = REQUEST_URL + I2C_SUB_URL;
	// I2C Requests
	String I2C_OPEN_URL = I2C_REQUEST_URL + "/open";
	String I2C_PROBE_URL = I2C_REQUEST_URL + "/probe";
	String I2C_WRITE_QUICK_URL = I2C_REQUEST_URL + "/writeQuick";
	String I2C_READ_BYTE_URL = I2C_REQUEST_URL + "/readByte";
	String I2C_WRITE_BYTE_URL = I2C_REQUEST_URL + "/writeByte";
	String I2C_READ_BYTES_URL = I2C_REQUEST_URL + "/read";
	String I2C_WRITE_BYTES_URL = I2C_REQUEST_URL + "/write";
	String I2C_READ_BYTE_DATA_URL = I2C_REQUEST_URL + "/readByteData";
	String I2C_WRITE_BYTE_DATA_URL = I2C_REQUEST_URL + "/writeByteData";
	String I2C_READ_WORD_DATA_URL = I2C_REQUEST_URL + "/readWordData";
	String I2C_WRITE_WORD_DATA_URL = I2C_REQUEST_URL + "/writeWordData";
	String I2C_PROCESS_CALL_URL = I2C_REQUEST_URL + "/processCall";
	String I2C_READ_BLOCK_DATA_URL = I2C_REQUEST_URL + "/readBlockData";
	String I2C_WRITE_BLOCK_DATA_URL = I2C_REQUEST_URL + "/writeBlockData";
	String I2C_BLOCK_PROCESS_CALL_URL = I2C_REQUEST_URL + "/blockProcessCall";
	String I2C_READ_I2C_BLOCK_DATA_URL = I2C_REQUEST_URL + "/readI2CBlockData";
	String I2C_WRITE_I2C_BLOCK_DATA_URL = I2C_REQUEST_URL + "/writeI2CBlockData";
	String I2C_CLOSE_URL = I2C_REQUEST_URL + "/close";
	// I2C Responses
	String I2C_READ_BYTE_RESPONSE_URL = RESPONSE_URL + "/i2cReadByte";
	String I2C_READ_RESPONSE_URL = RESPONSE_URL + "/i2cRead";

	// SPI
	String SPI_SUB_URL = "/spi";
	String SPI_REQUEST_URL = REQUEST_URL + SPI_SUB_URL;
	// SPI Requests
	String SPI_OPEN_URL = SPI_REQUEST_URL + "/open";
	String SPI_WRITE_URL = SPI_REQUEST_URL + "/write";
	String SPI_WRITE_AND_READ_URL = SPI_REQUEST_URL + "/writeAndRead";
	String SPI_CLOSE_URL = SPI_REQUEST_URL + "/close";
	// SPI Responses
	String SPI_RXDATA_RESPONSE_URL = RESPONSE_URL + "/spiRxData";

	// Serial
	String SERIAL_SUB_URL = "/serial";
	String SERIAL_REQUEST_URL = REQUEST_URL + SERIAL_SUB_URL;
	// Serial Requests
	String SERIAL_OPEN_URL = SERIAL_REQUEST_URL + "/open";
	String SERIAL_READ_URL = SERIAL_REQUEST_URL + "/read";
	String SERIAL_READ_BYTE_URL = SERIAL_REQUEST_URL + "/readByte";
	String SERIAL_WRITE_BYTE_URL = SERIAL_REQUEST_URL + "/writeByte";
	String SERIAL_READ_BYTES_URL = SERIAL_REQUEST_URL + "/readBytes";
	String SERIAL_WRITE_BYTES_URL = SERIAL_REQUEST_URL + "/writeBytes";
	String SERIAL_BYTES_AVAILABLE_URL = SERIAL_REQUEST_URL + "/bytesAvailable";
	String SERIAL_CLOSE_URL = SERIAL_REQUEST_URL + "/close";
	// Serial Responses
	String SERIAL_READ_RESPONSE_URL = RESPONSE_URL + "/serialRead";
	String SERIAL_READ_BYTE_RESPONSE_URL = RESPONSE_URL + "/serialReadByte";
	String SERIAL_READ_BYTES_RESPONSE_URL = RESPONSE_URL + "/serialReadBytes";
	String SERIAL_BYTES_AVAILABLE_RESPONSE_URL = RESPONSE_URL + "/serialBytesAvailable";
}
