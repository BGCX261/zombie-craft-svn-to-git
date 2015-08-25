<?php $options = get_option('site_basic_options'); ?>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		var Smm = {
    		fontDirectory: '<?php bloginfo("template_url") ?>/fonts/'
		};
		
		Smm.Fonts = {
		    LeagueGothic: {
		        cssFile: 'leaguegothic.css',
		        loadType: 'custom',
		        loaded: false,
		        name: 'LeagueGothicRegular'
		    },
		    MuseoSlab500: {
		        cssFile: 'museoslab.css',
		        loadType: 'custom',
		        loaded: false,
		        name: 'MuseoSlab500'
		    },
		    MuseoSans500: {
		        cssFile: 'museosans.css',
		        loadType: 'custom',
		        loaded: false,
		        name: 'MuseoSans500'
		    }
		};
		var fontCustom = false;
		for (var font in Smm.Fonts){
			if(Smm.Fonts[font]['name'] == "<?php echo $options["headerfont"] ?>"){
				fontCustom = true;
				break;
			}
		}		
		if (fontCustom){
	      WebFontConfig = {
	        custom: { 
		                families: [Smm.Fonts['<?php echo str_replace("+", "", $options["headerfont"]) ?>']['name']],
		                urls: [Smm.fontDirectory+Smm.Fonts['<?php echo str_replace("+", "", $options["headerfont"]) ?>']['cssFile']] 
		            }
	      };
	    }
	    else{
	      WebFontConfig = {
	        google: { families: [ '<?php echo $options["headerfont"] ?>'] }
	      };
	    }
      	(function() {
        var wf = document.createElement('script');
        wf.src = ('https:' == document.location.protocol ? 'https' : 'http') +
            '://ajax.googleapis.com/ajax/libs/webfont/1/webfont.js';
        wf.type = 'text/javascript';
        wf.async = 'true';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(wf, s);
      	})();
     });
      
      
      
    </script>
    <style type="text/css">
    
    body{
    	<?php if ($options['bodyfont'] == "sans-serif") : ?>
    	font-family:'Lucida Sans', 'Lucida Grande', 'Lucida Sans Unicode', sans-serif;;
    	<?php else: ?>
    	font-family:Georgia, sans-serif;
    	<?php endif; ?>
    }
      .wf-active h1, .wf-active h2, .wf-active h3, #brief p{
        font-family: '<?php echo str_replace("+", " ", $options["headerfont"]) ?>', sans-serif;
      }
      
      .wf-inactive h1, .wf-inactive h2, .wf-inactive h3, .wf-inactive h4{
      	font-family:"Helvetica Neue", Arial, Helvetica, sans-serif;
      }
    </style>