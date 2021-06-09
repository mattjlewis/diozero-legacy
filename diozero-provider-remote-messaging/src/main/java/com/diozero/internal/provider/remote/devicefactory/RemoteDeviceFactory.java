package com.diozero.internal.provider.remote.devicefactory;

/*-
 * #%L
 * Organisation: diozero
 * Project:      diozero - Remote Provider
 * Filename:     RemoteDeviceFactory.java
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

import java.util.UUID;

import org.tinylog.Logger;

import com.diozero.api.AnalogInputEvent;
import com.diozero.api.DeviceMode;
import com.diozero.api.DigitalInputEvent;
import com.diozero.api.GpioEventTrigger;
import com.diozero.api.GpioPullUpDown;
import com.diozero.api.I2CConstants;
import com.diozero.api.PinInfo;
import com.diozero.api.RuntimeIOException;
import com.diozero.api.SerialDevice;
import com.diozero.api.SpiClockMode;
import com.diozero.internal.provider.remote.firmata.FirmataProtocolHandler;
import com.diozero.internal.spi.AnalogInputDeviceInterface;
import com.diozero.internal.spi.AnalogOutputDeviceInterface;
import com.diozero.internal.spi.BaseNativeDeviceFactory;
import com.diozero.internal.spi.GpioDigitalInputDeviceInterface;
import com.diozero.internal.spi.GpioDigitalInputOutputDeviceInterface;
import com.diozero.internal.spi.GpioDigitalOutputDeviceInterface;
import com.diozero.internal.spi.InternalI2CDeviceInterface;
import com.diozero.internal.spi.InternalSerialDeviceInterface;
import com.diozero.internal.spi.InternalSpiDeviceInterface;
import com.diozero.internal.spi.PwmOutputDeviceInterface;
import com.diozero.internal.spi.SpiDeviceFactoryInterface;
import com.diozero.remote.message.GetBoardInfoRequest;
import com.diozero.remote.message.GetBoardInfoResponse;
import com.diozero.remote.message.GpioAnalogRead;
import com.diozero.remote.message.GpioAnalogReadResponse;
import com.diozero.remote.message.GpioAnalogWrite;
import com.diozero.remote.message.GpioClose;
import com.diozero.remote.message.GpioDigitalRead;
import com.diozero.remote.message.GpioDigitalReadResponse;
import com.diozero.remote.message.GpioDigitalWrite;
import com.diozero.remote.message.GpioEvents;
import com.diozero.remote.message.GpioGetPwmFrequency;
import com.diozero.remote.message.GpioGetPwmFrequencyResponse;
import com.diozero.remote.message.GpioInfo;
import com.diozero.remote.message.GpioPwmRead;
import com.diozero.remote.message.GpioPwmReadResponse;
import com.diozero.remote.message.GpioPwmWrite;
import com.diozero.remote.message.GpioSetPwmFrequency;
import com.diozero.remote.message.RemoteProtocolInterface;
import com.diozero.remote.message.Response;
import com.diozero.sbc.BoardInfo;

public class RemoteDeviceFactory extends BaseNativeDeviceFactory {
	public static final String DEVICE_NAME = "Remote";

	private RemoteProtocolInterface protocolHandler;

	public RemoteDeviceFactory() {
		// protocolHandler = new ProtobufMqttProtocolHandler(this);
		// protocolHandler = new JsonHttpProtocolHandler(this);
		protocolHandler = new FirmataProtocolHandler(this);
	}

	@Override
	public void start() {
		protocolHandler.start();
	}

	@Override
	public void shutdown() {
		Logger.trace("shutdown()");
		protocolHandler.close();
	}

	@Override
	public String getName() {
		return DEVICE_NAME;
	}

	@Override
	protected BoardInfo lookupBoardInfo() {
		return new RemoteBoardInfo(protocolHandler.request(new GetBoardInfoRequest(UUID.randomUUID().toString())));
	}

	@Override
	public int getBoardPwmFrequency() {
		Logger.warn("Not implemented");
		return -1;
	}

	@Override
	public void setBoardPwmFrequency(int frequency) {
		// Ignore
		Logger.warn("Not implemented");
	}

	@Override
	public int getSpiBufferSize() {
		// FIXME Add to protocol?
		return SpiDeviceFactoryInterface.DEFAULT_SPI_BUFFER_SIZE;
	}

	@Override
	public GpioDigitalInputDeviceInterface createDigitalInputDevice(String key, PinInfo pinInfo, GpioPullUpDown pud,
			GpioEventTrigger trigger) {
		return new RemoteDigitalInputDevice(this, key, pinInfo, pud, trigger);
	}

	@Override
	public GpioDigitalOutputDeviceInterface createDigitalOutputDevice(String key, PinInfo pinInfo,
			boolean initialValue) {
		return new RemoteDigitalOutputDevice(this, key, pinInfo, initialValue);
	}

	@Override
	public GpioDigitalInputOutputDeviceInterface createDigitalInputOutputDevice(String key, PinInfo pinInfo,
			DeviceMode mode) {
		return new RemoteDigitalInputOutputDevice(this, key, pinInfo, mode);
	}

	@Override
	public PwmOutputDeviceInterface createPwmOutputDevice(String key, PinInfo pinInfo, int pwmFrequency,
			float initialValue) {
		return new RemotePwmOutputDevice(this, key, pinInfo, pwmFrequency, initialValue);
	}

	@Override
	public AnalogInputDeviceInterface createAnalogInputDevice(String key, PinInfo pinInfo) {
		return new RemoteAnalogInputDevice(this, key, pinInfo);
	}

	@Override
	public AnalogOutputDeviceInterface createAnalogOutputDevice(String key, PinInfo pinInfo, float initialValue) {
		return new RemoteAnalogOutputDevice(this, key, pinInfo, initialValue);
	}

	@Override
	public InternalI2CDeviceInterface createI2CDevice(String key, int controller, int address,
			I2CConstants.AddressSize addressSize) throws RuntimeIOException {
		return new RemoteI2CDevice(this, key, controller, address, addressSize);
	}

	@Override
	public InternalSpiDeviceInterface createSpiDevice(String key, int controller, int chipSelect, int frequency,
			SpiClockMode spiClockMode, boolean lsbFirst) throws RuntimeIOException {
		return new RemoteSpiDevice(this, key, controller, chipSelect, frequency, spiClockMode, lsbFirst);
	}

	@Override
	public InternalSerialDeviceInterface createSerialDevice(String key, String deviceFile, int baud,
			SerialDevice.DataBits dataBits, SerialDevice.StopBits stopBits, SerialDevice.Parity parity,
			boolean readBlocking, int minReadChars, int readTimeoutMillis) throws RuntimeIOException {
		return new RemoteSerialDevice(this, key, deviceFile, baud, dataBits, stopBits, parity, readBlocking,
				minReadChars, readTimeoutMillis);
	}

	boolean digitalRead(int gpio) {
		GpioDigitalRead request = new GpioDigitalRead(gpio, UUID.randomUUID().toString());

		GpioDigitalReadResponse response = protocolHandler.request(request);
		if (response.getStatus() != Response.Status.OK) {
			throw new RuntimeIOException("Error in GPIO digital read: " + response.getDetail());
		}

		return response.getValue();
	}

	void digitalWrite(int gpio, boolean value) {
		GpioDigitalWrite request = new GpioDigitalWrite(gpio, value, UUID.randomUUID().toString());

		Response response = protocolHandler.request(request);
		if (response.getStatus() != Response.Status.OK) {
			throw new RuntimeIOException("Error in GPIO digital write: " + response.getDetail());
		}
	}

	float pwmRead(int gpio) {
		GpioPwmRead request = new GpioPwmRead(gpio, UUID.randomUUID().toString());

		GpioPwmReadResponse response = protocolHandler.request(request);
		if (response.getStatus() != Response.Status.OK) {
			throw new RuntimeIOException("Error in GPIO PWM read: " + response.getDetail());
		}

		return response.getValue();
	}

	void pwmWrite(int gpio, float value) {
		GpioPwmWrite request = new GpioPwmWrite(gpio, value, UUID.randomUUID().toString());

		Response response = protocolHandler.request(request);
		if (response.getStatus() != Response.Status.OK) {
			throw new RuntimeIOException("Error in GPIO PWM write: " + response.getDetail());
		}
	}

	int getPwmFrequency(int gpio) {
		GpioGetPwmFrequency request = new GpioGetPwmFrequency(gpio, UUID.randomUUID().toString());

		GpioGetPwmFrequencyResponse response = protocolHandler.request(request);
		if (response.getStatus() != Response.Status.OK) {
			throw new RuntimeIOException("Error in GPIO PWM read: " + response.getDetail());
		}

		return response.getFrequency();
	}

	void setPwmFrequency(int gpio, int frequency) {
		GpioSetPwmFrequency request = new GpioSetPwmFrequency(gpio, frequency, UUID.randomUUID().toString());

		Response response = protocolHandler.request(request);
		if (response.getStatus() != Response.Status.OK) {
			throw new RuntimeIOException("Error in GPIO set PWM frequency: " + response.getDetail());
		}
	}

	float analogRead(int gpio) {
		GpioAnalogRead request = new GpioAnalogRead(gpio, UUID.randomUUID().toString());

		GpioAnalogReadResponse response = protocolHandler.request(request);
		if (response.getStatus() != Response.Status.OK) {
			throw new RuntimeIOException("Error in GPIO analog read: " + response.getDetail());
		}

		return response.getValue();
	}

	void analogWrite(int gpio, float value) {
		GpioAnalogWrite request = new GpioAnalogWrite(gpio, value, UUID.randomUUID().toString());

		Response response = protocolHandler.request(request);
		if (response.getStatus() != Response.Status.OK) {
			throw new RuntimeIOException("Error in GPIO analog write: " + response.getDetail());
		}
	}

	void enableEvents(int gpio, boolean b) {
		GpioEvents request = new GpioEvents(gpio, b, UUID.randomUUID().toString());

		Response response = protocolHandler.request(request);
		if (response.getStatus() != Response.Status.OK) {
			throw new RuntimeIOException("Error reading GPIO: " + response.getDetail());
		}
	}

	public void accept(DigitalInputEvent event) {
		PinInfo pin_info = getBoardPinInfo().getByGpioNumberOrThrow(event.getGpio());
		RemoteDigitalInputDevice device = getDevice(createPinKey(pin_info));
		if (device != null) {
			device.accept(event);
		}
	}

	public void accept(AnalogInputEvent event) {
		PinInfo pin_info = getBoardPinInfo().getByGpioNumberOrThrow(event.getGpio());
		RemoteAnalogInputDevice device = getDevice(createPinKey(pin_info));
		if (device != null) {
			device.accept(event);
		}
	}

	void closeGpio(int gpio) {
		GpioClose request = new GpioClose(gpio, UUID.randomUUID().toString());

		Response response = protocolHandler.request(request);
		if (response.getStatus() != Response.Status.OK) {
			Logger.error("Error closing device: " + response.getDetail());
		}
	}

	RemoteProtocolInterface getProtocolHandler() {
		return protocolHandler;
	}

	static class RemoteBoardInfo extends BoardInfo {
		private GetBoardInfoResponse boardInfoResponse;

		public RemoteBoardInfo(GetBoardInfoResponse boardInfoResponse) {
			super(boardInfoResponse.getMake(), boardInfoResponse.getModel(), boardInfoResponse.getMemory(),
					boardInfoResponse.getAdcVref(), "remote");

			this.boardInfoResponse = boardInfoResponse;

			populateBoardPinInfo();
		}

		@Override
		public void populateBoardPinInfo() {
			for (GpioInfo gpio_info : boardInfoResponse.getGpioPins()) {
				if (gpio_info.getModes().contains(DeviceMode.PWM_OUTPUT)) {
					addPwmPinInfo(gpio_info.getHeader(), gpio_info.getGpioNumber(), gpio_info.getName(),
							gpio_info.getPhysicalPin(), gpio_info.getPwmNum(), gpio_info.getModes());
				} else {
					addGpioPinInfo(gpio_info.getHeader(), gpio_info.getGpioNumber(), gpio_info.getName(),
							gpio_info.getPhysicalPin(), gpio_info.getModes());
				}
			}
			for (GpioInfo gpio_info : boardInfoResponse.getAdcPins()) {
				addAdcPinInfo(gpio_info.getHeader(), gpio_info.getGpioNumber(), gpio_info.getName(),
						gpio_info.getPhysicalPin());
			}
			for (GpioInfo gpio_info : boardInfoResponse.getDacPins()) {
				addDacPinInfo(gpio_info.getHeader(), gpio_info.getGpioNumber(), gpio_info.getName(),
						gpio_info.getPhysicalPin());
			}
		}
	}
}
