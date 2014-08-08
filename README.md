TrackingNumbers
===============

For Handling Tracking Numbers

A manufacturer keeps an ordered table of tracking numbers by listing in each row of the table a range of tracking numbers along with two corresponding pieces of information called the status code and the transfer code. A four-column table stores information about ranges of tracking numbers in this order: starting tracking number, ending tracking number, status code, transfer code. Tracking numbers as well as transfer codes are integers from 1 to 231-1 (231-1 = 2147483647), and status codes are a single upper-case letter. The table is maintained in increasing order of tracking numbers, tracking number ranges are never allowed to overlap, and for any given tracking number, the table must always accurately represent the most recent data (status code and transfer code) for that tracking number.
Let's say that 100,000 tracking numbers are created with a status of "A" and a transfer code of "1". An entry for those tracking numbers might look like this:

Range Class
split()
merge()
isSplitRequired()
isMergeRequired()
