<!DOCTYPE html>
<html>
    <head>
        <style>
        .sliders {
    display: inline-block;
    zoom: 1; *display: inline;
    width: 210px;
    vertical-align: middle;
    overflow: hidden;
}
.color {
    display: inline-block;
    zoom:1; *display: inline;
    width: 100px;
    height: 100px;
    border: 1px solid rgba(0, 0, 0, 0.5);
    -webkit-box-shadow: 1px 1px 2px 0px rgba(0, 0, 0, 0.3);
    -moz-box-shadow: 1px 1px 2px 0px rgba(0, 0, 0, 0.3);
    box-shadow: 1px 1px 2px 0px rgba(0, 0, 0, 0.3);
    margin: 0 20px;
    vertical-align: middle;
}
.output {
    display: inline-block;
    zoom: 1; *display: inline;
    vertical-align: middle;
    overflow: hidden;
}
.sliders dt,
.output dt {
    float: left;
    clear: left;
    width: 50px;
    height: 40px;
    line-height: 40px;
}
.sliders dd,
.output dd {
    float: left;
    height: 40px;
    line-height: 40px;
    margin: 0;
}

        </style>
        <script src="http://yui.yahooapis.com/3.18.1/build/yui/yui-min.js"></script>
        <script>
		YUI()
				.use(
						'slider',
						'color',
						function(Y) {
							// sliders
							var rSlider = new Y.Slider({
								min : 0,
								max : 255,
								value : Math.round(Math.random() * 255)
							}), gSlider = new Y.Slider({
								min : 0,
								max : 255,
								value : Math.round(Math.random() * 255)
							}), bSlider = new Y.Slider({
								min : 0,
								max : 255,
								value : Math.round(Math.random() * 255)
							}),

							// slider values
							rVal = Y.one('#r-val'), gVal = Y.one('#g-val'), bVal = Y
									.one('#b-val'),

							// color strings
							hex = Y.one('#hex'), rgb = Y.one('#rgb'), hsl = Y
									.one('#hsl'),

							// color chip
							color = Y.one('.color');

							// render sliders
							rSlider.render('#r-slider');
							gSlider.render('#g-slider');
							bSlider.render('#b-slider');

							// register update events
							rSlider.after('thumbMove', function(e) {
								rVal.set('text', rSlider.get('value'));
								updateColors();
							});
							gSlider.after('thumbMove', function(e) {
								gVal.set('text', gSlider.get('value'));
								updateColors();
							});
							bSlider.after('thumbMove', function(e) {
								bVal.set('text', bSlider.get('value'));
								updateColors();
							});

							// update the colors strings
							function updateColors() {
								var r = rSlider.get('value'), g = gSlider
										.get('value'), b = bSlider.get('value'), rgbStr = Y.Color
										.fromArray([ r, g, b ],
												Y.Color.TYPES.RGB);

								color.setStyle('backgroundColor', rgbStr);

								rgb.set('text', rgbStr);

								hex.set('text', Y.Color.toHex(rgbStr));
								hsl.set('text', Y.Color.toHSL(rgbStr));
							}

							rVal.set('text', rSlider.get('value'));
							gVal.set('text', gSlider.get('value'));
							bVal.set('text', bSlider.get('value'));
							updateColors();

						});
	</script>
    </head>
    <body>
        <div class="sliders yui3-skin-sam">
    <dl>
        <dt>R: <span id="r-val" class="val"></span></dt><dd id="r-slider">123</dd>
        <dt>G: <span id="g-val" class="val"></span></dt><dd id="g-slider"></dd>
        <dt>B: <span id="b-val" class="val"></span></dt><dd id="b-slider"></dd>
    </dl>
</div>
<div class="color"></div>
<div class="output">
    <dl>
        <dt>Hex:</dt><dd id="hex"></dd>
        <dt>RGB:</dt><dd id="rgb"></dd>
        <dt>HSL:</dt><dd id="hsl"></dd>
    </dl>
</div>

	
</body>
</html>