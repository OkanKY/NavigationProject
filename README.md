# NavigationProject
![alt tag](https://OkanKY.github.io/NavigationProject/blob/master/uml.png)
1-) Map.json dosyasında birbirine doğrudan bağlı olan şehirler, bu şehirler arasındaki mesafe ve ortalama trafik akış hızları kayıtlıdır. 
Map.json
{
"map":
[{"city1":"A", "city2":"B", "distance":30,"avgSpeed":30},
{"city1":"A", "city2":"C", "distance":50,"avgSpeed":30},
{"city1":"B", "city2":"D", "distance":20,"avgSpeed":30},
{"city1":"B", "city2":"F", "distance":90,"avgSpeed":30},
{"city1":"C", "city2":"D", "distance":40,"avgSpeed":30},
{"city1":"D", "city2":"E", "distance":10,"avgSpeed":30},
{"city1":"E", "city2":"F", "distance":60,"avgSpeed":30},
{"city1":"F", "city2":"G", "distance":70,"avgSpeed":30},
{"city1":"F", "city2":"H", "distance":70,"avgSpeed":30},
{"city1":"J", "city2":"L", "distance":30,"avgSpeed":30},
{"city1":"J", "city2":"K", "distance":20,"avgSpeed":30},
{"city1":"K", "city2":"L", "distance":40,"avgSpeed":30}]
}
2-) Verilen iki şehir arasında bir yol olup olmadığını bulan fonksiyon Aşağıda gösterilmiştir . Bu fonksiyona A ve F verildiğinde “true” ; D ve J verildiğinde “false” yanıt dönmektedir
//Mesafe uzunluğuna göre arama yapılmıştır.
ArrayList<Vertex> verticesDistance=jParse.getVertices(true);
//The starting point is selected
//Dosyadan okunan ilk eleman yani “A” şehiri seçilmitir.
Dijkstra.computePaths(verticesDistance.get(0));
// Dosyadan okunan ikinçi eleman yani “B” şehiri hedef şehir seçilmitir.
//A şehirinden B şehirine yol olup olmadığına bakılmıştır.
System.out.println("Have Path : " + Dijkstra.getPathTo(verticesDistance.get(1)));
/*
Ekran çıktısı:
Path: [A, B]
Have Path : true
*/
public static Boolean getPathTo(Vertex target)
{ if(target.previous==null)
return false;
return true;
}
.
3-) Verilen iki şehir arasındaki tüm yolları ve bu yolların toplam uzunluklarını listeleyen fonksiyon aşağıda gösterilmiştir.
//Mesafe uzunluğuna göre arama yapılmıştır.
ArrayList<Vertex> verticesDistance=jParse.getVertices(true);
//The starting point is selected
//Dosyadan okunan ilk eleman yani “A” şehiri seçilmitir.
Dijkstra.computePaths(verticesDistance.get(0));
// Dosyadan okunan ikinçi eleman yani “B” şehiri hedef şehir seçilmitir.
System.out.println("Distance to " + verticesDistance.get(1) + ": " + verticesDistance.get(1).minDistance);
/*
Ekran çıktısı:
Path: [A, B]
Distance to B: 30.0
*/
*/
public static void computePaths(Vertex source)
{
source.minDistance = 0.;
PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
vertexQueue.add(source);
while (!vertexQueue.isEmpty()) {
Vertex u = vertexQueue.poll();
// Visit each edge exiting u
for (Edge e : u.adjacencies)
{
Vertex v = e.target;
double weight = e.weight;
double distanceThroughU = u.minDistance + weight;
if (distanceThroughU < v.minDistance) {
vertexQueue.remove(v);
v.minDistance = distanceThroughU ;
v.previous = u;
vertexQueue.add(v);
}
}
}
}
public static List<Vertex> getShortestPathTo(Vertex target)
{
List<Vertex> path = new ArrayList<Vertex>();
for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
path.add(vertex);
Collections.reverse(path);
return path;
}
4-)Harita bilgisinde her yolun ortalama hız bilgisinin  de saklanmaktadır. Örneğin,
{"city1":"A", "city2":"B", "distance":30,"avgSpeed":30}, A ve B şehirleri arasında trafiğin ortalama 60km/s hızla seyrettiğini göstermektedir. En iyi yolu listeleyen fonksiyon aşağıda gösterilmiştir.
/*
Kullanıcı getVertices() fonksiyonunu kullanmak istediğinde mesafe yada ortalama trafik akış hızına göre tercih etmektedir.
Boolean distance True girilirse mesafeye bağımlı çalışır
Yada False girildiğinde trafik akış hızına bağımlı çalışır
Aşağıdaki kod bloğu bu görevi üstlenmektedir.
//if Selected distance, record distance
if(distance)
v1.adjacencies.add(new Edge(v2, Double.parseDouble(innerObj.get("distance").toString())));
//if Selected average speed, record average speed
else
v1.adjacencies.add(new Edge(v2, Double.parseDouble(innerObj.get("distance").toString())/Double.parseDouble(innerObj.get("avgSpeed").toString())));
*/
public ArrayList<Vertex> getVertices(Boolean distance)
{
try {
// read the json file
FileReader reader = new FileReader(filePath);
JSONParser jsonParser = new JSONParser();
JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
// get an array from the JSON object
JSONArray lang= (JSONArray) jsonObject.get("map");
//use Iterator Pattern
Iterator i = lang.iterator();
// take each value from the json array separately
while (i.hasNext()) {
JSONObject innerObj = (JSONObject) i.next();
Vertex v1,v2;
if(getInculude(innerObj.get("city1").toString())==null)
{
v1 = new Vertex(""+innerObj.get("city1"));
v1.adjacencies = new ArrayList<Edge>();
vertices.add(v1);
}
else
v1 = getInculude(innerObj.get("city1").toString());
if(getInculude(innerObj.get("city2").toString())==null)
{
v2 = new Vertex(""+innerObj.get("city2"));
v2.adjacencies = new ArrayList<Edge>();
vertices.add(v2);
}
else
v2 = getInculude(innerObj.get("city2").toString());
//if Selected distance, record distance
if(distance)
v1.adjacencies.add(new Edge(v2, Double.parseDouble(innerObj.get("distance").toString())));
//if Selected average speed, record average speed
else
v1.adjacencies.add(new Edge(v2, Double.parseDouble(innerObj.get("distance").toString())/Double.parseDouble(innerObj.get("avgSpeed").toString())));
}
} catch (FileNotFoundException ex) {
ex.printStackTrace();
} catch (IOException ex) {
ex.printStackTrace();
} catch (ParseException ex) {
ex.printStackTrace();
} catch (NullPointerException ex) {
ex.printStackTrace();
}
return vertices;
}
.
5-)Kod otomatik test yöntemleriyle JUnit kullanılarak test edilmiştir.
Kullanılan Test Classları Aşağıdadır.
/*
Map.json Dosyasından okunan verilerin doğrulu kontrol edilmektedir.
*/
public class JsonParseTest {
@Test
public void jsonParseTest() {
JsonParse json =new JsonParse(); String[] VertexArray = {"A","B","C","D"};
int i=0;
for (Vertex v : json.getVertices(true))
{
String expected=v.name; String actual =VertexArray[i];
Assert.assertEquals(expected, actual);
i++;
}
}
}
/*
Dosyadan okunan şehirler arasında yol olup olmadığı, bulunan en kısa yolun doğruluğu, En kısa yolun hangi şehirlerden geçtiğini kontrol edilmektedir.
*/
public class NavigationTest {
private ArrayList<Vertex> getVertices()
{
//implementation
Vertex v0 = new Vertex("A");
Vertex v1 = new Vertex("B");
v0.adjacencies = new ArrayList<Edge>();
v0.adjacencies.add(new Edge(v1, 5.0));
v1.adjacencies = new ArrayList<Edge>();
// ArrayList<Vertex> ArrayList<Vertex> vertices = new ArrayList<Vertex>(); vertices.add(v0); vertices.add(v1); return vertices;
}
@Test
public void testMinDistance() {
ArrayList<Vertex> vertices=getVertices();
Dijkstra.computePaths(vertices.get(0));
Double expected= (double) 5;
Double actual=vertices.get(1).minDistance;
Assert.assertEquals(expected, actual);
}
@Test
public void testShortestPath()
{
//Show Short Path A and B
ArrayList<Vertex> vertices=getVertices();
Dijkstra.computePaths(vertices.get(0));
String expected=Dijkstra.getShortestPathTo(vertices.get(1)).toString();
String actual ="[A, B]";
Assert.assertEquals(expected, actual);
}
@Test
public void testHavePath()
{
//Have A and B Path Control
ArrayList<Vertex> vertices=getVertices();
Dijkstra.computePaths(vertices.get(0));
Boolean expected=Dijkstra.getPathTo(vertices.get(1));
Boolean actual =true;
Assert.assertEquals(expected, actual);
}
}
@RunWith(Suite.class)
@SuiteClasses({ JsonParseTest.class, NavigationTest.class })
public class AllTests {
}
 Var olan şehirlerin ilgili bilgilerine erişmek için aşağıda Navigation Classı kullanılmıştır. En son olarak ekran çıktısı yer almaktadır.
public class Navigation {
public static void main(String[] args)
{
JsonParse jParse =new JsonParse();
/*
*
* Selected distance
*
* */
System.out.println("*** According to calculation minimum Distance ***");
//data is read from the file
ArrayList<Vertex> verticesDistance=jParse.getVertices(true);
//The starting point is selected
Dijkstra.computePaths(verticesDistance.get(0));
//Shows all path
for (Vertex v : verticesDistance)
{
System.out.println("Distance to " + v + ": " + v.minDistance);
List<Vertex> path = Dijkstra.getShortestPathTo(v);
System.out.println("Path: " + path);
System.out.println("Have Path : " + Dijkstra.getPathTo(v));
}
/*
*
* Selected average speed
*
* */
System.out.println("*** According to calculation minimum Average Speed ***");
//data is read from the file
ArrayList<Vertex> verticesAvgSpeed=jParse.getVertices(false);
//The starting point is selected
Dijkstra.computePaths(verticesAvgSpeed.get(0));
//Shows all path
for (Vertex v : verticesAvgSpeed)
{
System.out.println("average speed to " + v + ": " + v.minDistance);
List<Vertex> path = Dijkstra.getShortestPathTo(v);
System.out.println("Path: " + path);
System.out.println("Have Path : " + Dijkstra.getPathTo(v));
}
}
}
/*
Ekran Çıktısı:
*** According to calculation minimum Distance ***
Distance to A: 0.0
Path: [A]
Have Path : false
Distance to B: 30.0
Path: [A, B]
Have Path : true
Distance to C: 50.0
Path: [A, C]
Have Path : true
Distance to D: 50.0
Path: [A, B, D]
Have Path : true
Distance to F: 120.0
Path: [A, B, F]
Have Path : true
Distance to E: 60.0
Path: [A, B, D, E]
Have Path : true
Distance to G: 190.0
Path: [A, B, F, G]
Have Path : true
Distance to H: 190.0
Path: [A, B, F, H]
Have Path : true
Distance to J: Infinity
Path: [J]
Have Path : false
Distance to L: Infinity
Path: [L]
Have Path : false
Distance to K: Infinity
Path: [K]
Have Path : false
*** According to calculation minimum Average Speed ***
average speed to A: 0.0
Path: [A]
Have Path : false
average speed to B: 1.0
Path: [A, B]
Have Path : true
average speed to C: 1.6666666666666667
Path: [A, C]
Have Path : true
average speed to D: 1.6666666666666665
Path: [A, B, D]
Have Path : true
average speed to F: 4.0
Path: [A, B, F]
Have Path : true
average speed to E: 1.9999999999999998
Path: [A, B, D, E]
Have Path : true
average speed to G: 6.333333333333334
Path: [A, B, F, G]
Have Path : true
average speed to H: 6.333333333333334
Path: [A, B, F, H]
Have Path : true
average speed to J: Infinity
Path: [J]
Have Path : false
average speed to L: Infinity
Path: [L]
Have Path : false
average speed to K: Infinity
Path: [K]
Have Path : false
*/
