      implicit none

      integer a, b
      a = 3
      b = 4

      if ( a .lt. b ) then
      a = b
      else
      b = a
      endif