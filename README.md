WiseCapturer
============

If you're looking to create a tool like Gyazo, which captures a selected area and uploads, or a Gif capturer tool like GyazoGif, you're looking at the right repository.

WiseCapturer gives you a full control of selecting an area, capturing a gif, and a screenshot, and simply get the captured file with a few of line calls.


Get Started
===========

Firstly, for your capturing instance, you need to select a capturer - there are currently two capturing tools:


ScreenshotCapturer
------------------

Captures a simple PNG image of the selected area.

**Hotkeys**
`ESCAPE` to cancel selection

GifCapturer 
-----------
Captures a gif animation image of the selected area

**Hotkeys** 
`ESCAPE` to cancel selection
`ENTER` to finish capture, and save.


Creating the instance
---------------------

`Bootstrap` is the main class in WiseCapturer, therefore you need to create a new instance of it.
Bootstrap requires an instance of `Capturer`, any of the capturers I have included in this library.

For instance:

		Bootstrap b = new Bootstrap(new ScreenshotCapturer());

Capturing an area
-----------------

To capture an area, you need to call `Bootstrap#beginCapture` method which requires an instance of `ScreenCaptureCallback`

for instance:

		Bootstrap b = new Bootstrap(new GifCapturer());
		b.beginCapture(new ScreenCaptureCallback() {
			@Override
			public void captureEnded(CapturedImage image) {
				System.out.println("Hello World!");
			}
		});

In the example above, beginCapturer recevies a new instance of `ScreenCaptureCallback`, that class gets the CapturedImage object from the library, it basiaclly listens and waits till the capturer sends the captured result.




`ScreenCaptureCallback#captureEnded` gets called by the `Capturer` class, when the capturing process has ended and is ready to be sent.

The file you receive as a parameter in `ScreenCaptureCallback~#captureEnded` is the ready file image, you either upload it, or convert it to BufferedImage and do whatever you want. The file is saved into a temporary file, therefore you can't have multiple screenshots at a time.
