package com.diozero.internal.provider.mmap;

/*
 * #%L
 * Organisation: diozero
 * Project:      diozero - High performance mmap GPIO control
 * Filename:     MmapDigitalInputOutputDevice.java
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


import com.diozero.api.DeviceMode;
import com.diozero.api.GpioPullUpDown;
import com.diozero.api.PinInfo;
import com.diozero.api.RuntimeIOException;
import com.diozero.internal.provider.builtin.SysFsDigitalInputOutputDevice;
import com.diozero.internal.spi.MmapGpioInterface;

public class MmapDigitalInputOutputDevice extends SysFsDigitalInputOutputDevice {
	private MmapDeviceFactory mmapDeviceFactory;
	private MmapGpioInterface mmapGpio;
	private GpioPullUpDown pud;

	public MmapDigitalInputOutputDevice(MmapDeviceFactory deviceFactory, String key,
			PinInfo pinInfo, DeviceMode mode) {
		super(deviceFactory, key, pinInfo, mode);
		
		this.mmapDeviceFactory = deviceFactory;
		mmapGpio = mmapDeviceFactory.getMmapGpio();

		// For when mode is set to input
		this.pud = GpioPullUpDown.NONE;
		
		setMode(mode);
	}

	@Override
	public void setMode(DeviceMode mode) {
		super.setMode(mode);
		
		if (mode == DeviceMode.DIGITAL_INPUT) {
			mmapGpio.setPullUpDown(gpio, pud);
		}
	}

	@Override
	public boolean getValue() throws RuntimeIOException {
		return mmapGpio.gpioRead(gpio);
	}

	@Override
	public void setValue(boolean value) throws RuntimeIOException {
		if (mode != DeviceMode.DIGITAL_OUTPUT) {
			throw new IllegalStateException("Can only set output value for digital output pins (mode=" + mode + ")");
		}
		mmapGpio.gpioWrite(gpio, value);
	}
}