// LM35
float sicaklik; //Analog değeri dönüştüreceğimiz sıcaklık 
float olculendeger; //Ölçeceğimiz analog değer

int sicaklikDusuk = 30;
int sicaklikOrta = 31;
int sicaklikYuksek = 32;

int nemDusuk = 38;
int nemOrta = 37;
int nemYuksek = 36;

int ledYak = 39;



// YL-39 + YL-69 humidity sensor
byte humidity_sensor_pin = A2;
byte humidity_sensor_vcc = 6;

void setup()
{
// Init the humidity sensor board
pinMode(humidity_sensor_vcc, OUTPUT);
digitalWrite(humidity_sensor_vcc, LOW);

// Setup Serial
while (!Serial);
delay(1000);
Serial.begin(9600);

 
pinMode(sicaklikDusuk, OUTPUT);
pinMode(sicaklikOrta, OUTPUT);
pinMode(sicaklikYuksek, OUTPUT);

pinMode(nemDusuk, OUTPUT);
pinMode(nemOrta, OUTPUT);
pinMode(nemYuksek, OUTPUT);

pinMode(ledYak, OUTPUT);

}

// YL-39 + YL-69 humidity sensor
int read_humidity_sensor() {
digitalWrite(humidity_sensor_vcc, HIGH);
delay(500);
int value = analogRead(humidity_sensor_pin);
digitalWrite(humidity_sensor_vcc, LOW);
return 1023 - value;
}

void loop()
{

  if (Serial.available() > 0) {
                // gelen veriyi oku
                int gelenVeri = Serial.read();
              if(gelenVeri == 65){
                digitalWrite(ledYak,HIGH);
              }
              if(gelenVeri == 66){
                digitalWrite(ledYak,LOW);
              }
        }

        
/*ISI*/
//olculendeger = analogRead(A0); //A1'den değeri alacak
//olculendeger = (olculendeger/1023)*5000;//değeri mV'a dönüştürecek 
//sicaklik = olculendeger /10,0; // mV'u sicakliğa dönüştürecek

int okunanDeger = analogRead(A1);          //analog deger okundu ve okunanDeger değişkenine atandı
float sicaklik = okunanDeger / 9.31;         //derece hesaplandı
Serial.print("Degree :");
Serial.print (sicaklik);

if(sicaklik <=15){
  digitalWrite(sicaklikDusuk, HIGH);
  digitalWrite(sicaklikOrta, LOW);
  digitalWrite(sicaklikYuksek, LOW);
}
else if(sicaklik >15 && sicaklik<20){
  digitalWrite(sicaklikDusuk, LOW);
  digitalWrite(sicaklikOrta, HIGH);
  digitalWrite(sicaklikYuksek, LOW);
}
else{
  digitalWrite(sicaklikDusuk, LOW);
  digitalWrite(sicaklikOrta, LOW);
  digitalWrite(sicaklikYuksek, HIGH);
}

delay (3000);


// YL-39 + YL-69 humidity sensor
Serial.print("\nHumidity :");
Serial.println(read_humidity_sensor());

if(read_humidity_sensor() <=500){
  digitalWrite(nemDusuk, HIGH);
  digitalWrite(nemOrta, LOW);
  digitalWrite(nemYuksek, LOW);
}
else if(read_humidity_sensor() >500 && read_humidity_sensor()<680){
  digitalWrite(nemDusuk, LOW);
  digitalWrite(nemOrta, HIGH);
  digitalWrite(nemYuksek, LOW);
}
else{
  digitalWrite(nemDusuk, LOW);
  digitalWrite(nemOrta, LOW);
  digitalWrite(nemYuksek, HIGH);
}

        

delay (1000);
}
