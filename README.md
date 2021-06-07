# names-ml
A machine learning classifier that identifies if a given name is from one category or another, when configured with two classifications of names.

## How It Works
The algorithm mainly uses the Naive Bayes algorithm (which uses [Bayes' Rule](https://en.wikipedia.org/wiki/Bayes%27_theorem "Bayes' Rule") to consider the probability of a 
given name being from one category or another. This project's datasets include a set of Indian names as well as set of Spanish surnames (see Acknowledgments)

### Naive Bayes
Below is the Naive Bayes calculation, where <img src="https://render.githubusercontent.com/render/math?math=I"> is the event that a name is from the category of interest and <img src="https://render.githubusercontent.com/render/math?math=S">
is the event that a given substring appears in any name.
<br />
<br />
<img src="https://latex.codecogs.com/gif.latex?%5Cmathbb%7BP%7D%28I%20%7C%20S%29%20%3D%20%5Cfrac%7B%5Cmathbb%7BP%7D%28S%20%7C%20I%29%20%5Ccdot%20%5Cmathbb%7BP%7D%28I%29%7D%7B%5Cmathbb%7BP%7D%28S%20%7C%20I%29%20%5Ccdot%20%5Cmathbb%7BP%7D%28I%29%20&plus;%20%5Cmathbb%7BP%7D%28S%20%7C%20%5Cbar%20I%29%20%5Ccdot%20%5Cmathbb%7BP%7D%28%5Cbar%20I%29%7D" />

The original idea was to consider all the individual characters in a name and the probability
that it appears in a name of the category of interest. However, this can lead to issues where two names of different classifications
have the exact same characters, but different spellings. Consider the 
following case:

<img src="https://latex.codecogs.com/gif.latex?%5Ctext%7BA%20%7D%5Ctext%7BR%20%7D%5Ctext%7BU%20%7D%5Ctext%7BL%20%7D" />
<br />
<img src="https://latex.codecogs.com/gif.latex?%5Ctext%7BR%20%7D%5Ctext%7BA%20%7D%5Ctext%7BU%20%7D%5Ctext%7BL%20%7D" />*
<br />

The solution to the above case would be to consider the position of each character
relative to other characters. For example, instead of taking into account the set
of letters {A, R, U, L}, we can instead consider the set of three-letter substrings 
present in the name, i.e {ARU, RUL}. The name RAUL would be considered as {RAU, AUL}.
This approach also accounts for duplicate letters and their positions (such as {AAN} vs {ANA}).
<br />
<br />
*This is for illustrative purposes only. The actual spelling of 'Raúl' contains an accent over the 'u'
which would be successful differentiated from 'u' by the algorithm.


## Limitations
One of the limitations of this project is that there are only two types of names being considered.
When given a name that is not from any of the two classifications, the algorithm is likely to return
an incorrect label.

## Use Cases
This algorithm can easily be adapted for larger purposes. The most immediate would be language
detection, where, given an input text, the algorithm would return the langauge of the text.

## Acknowledgements
Indian names datasets forked from @[balasahebgulave](https://github.com/DavidSRoy/Dataset-Indian-Names "Dataset-Indian-Names")
<br />
Spanish names dataset forked from @[jvalhondo](https://github.com/jvalhondo/spanish-names-surnames "spanish-names-surnames")
