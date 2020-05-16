      implicit none

      integer i, a, b
      logical d
      a = 3
      b = 0

      b = b + 1
      d = b .gt. 10


      do i = 0, 10
        b = i + 1
      enddo

      b = 0

      end