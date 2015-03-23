function safeSetInterval(f, d) { function f1() { f(); setTimeout(f1, d); } f1();}
